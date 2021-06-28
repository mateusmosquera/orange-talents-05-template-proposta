package br.com.zupacademy.mateus.proposta.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import br.com.zupacademy.mateus.proposta.Client.CartoesClient;
import br.com.zupacademy.mateus.proposta.Client.PropostaClient;
import br.com.zupacademy.mateus.proposta.config.security.DocumentoEncrypter;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoAnalise;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;
import br.com.zupacademy.mateus.proposta.controller.dto.PropostaDto;
import br.com.zupacademy.mateus.proposta.model.Proposta;
import br.com.zupacademy.mateus.proposta.model.SituacaoFinanceira;
import br.com.zupacademy.mateus.proposta.repository.PropostaRepository;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private PropostaClient propostaClient;

	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

	private final Tracer tracer;

	public PropostaController(Tracer tracer) {
		this.tracer = tracer;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PropostaDto> cadastrar(@RequestBody @Valid PropostaDto propostaDto,
			UriComponentsBuilder uriBuilder) throws IOException {

		if (!propostaDto.documentoIsUnique(propostaRepository)) {
			logger.warn("Proposta já tem documento cadastrado! documento={}", propostaDto.getDocumento());
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já cadsatrado");
		}
		String documento = propostaDto.getDocumento();
		propostaDto.setDocumento(DocumentoEncrypter.encriptarDocumento(documento));

		Proposta newProp = propostaDto.toModel();

		SolicitacaoAnalise solicitanteAnalise = new SolicitacaoAnalise(documento,
				newProp.getNome(),newProp.getId());

		SolicitacaoResponse solicitacaoResponse;
		try {
			solicitacaoResponse = propostaClient.solicitarDados(solicitanteAnalise);
		}catch (FeignException e){
			logger.error(e.getMessage());
			solicitacaoResponse = new ObjectMapper().readValue(e.responseBody().get().array(),SolicitacaoResponse.class);
		}
			newProp.alocarDadosFinanceiros(solicitacaoResponse.getResultadoSolicitacao());

			propostaRepository.save(newProp);

			gerenciarPropostaJaeger(newProp);

			URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(newProp.getId()).toUri();

			return ResponseEntity.created(uri).body(propostaDto);
	}

	@GetMapping
	public ResponseEntity<Proposta> consultar(@PathParam("id") @NotNull Long id){

		Optional<Proposta> optProposta = propostaRepository.findById(id);

		if(optProposta.isEmpty()){
			throw new ApiErroException(HttpStatus.NOT_FOUND, "Proposta não encontrada");
		}
		return ResponseEntity.ok(optProposta.get());
	}

	public void gerenciarPropostaJaeger(Proposta newProp){
		Span activeSpan = tracer.activeSpan();

		activeSpan.setTag("user.email", newProp.getEmail());

		activeSpan.setBaggageItem("user.email", newProp.getEmail());

		activeSpan.log("Span e Baggage setado para o email "+newProp.getEmail()+" com sucesso");
	}

}
