package br.com.zupacademy.mateus.proposta.controller;

import java.net.URI;

import javax.validation.Valid;

import br.com.zupacademy.mateus.proposta.Client.CartoesClient;
import br.com.zupacademy.mateus.proposta.Client.PropostaClient;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoAnalise;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

	@PostMapping
	@Transactional
	public ResponseEntity<PropostaDto> cadastrar(@RequestBody @Valid PropostaDto propostaDto,
			UriComponentsBuilder uriBuilder) {
		if (!propostaDto.documentoIsUnique(propostaRepository)) {
			logger.warn("Proposta já tem documento cadastrado! documento={}", propostaDto.getDocumento());
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já cadsatrado");
		}
		Proposta newProp = propostaDto.toModel();

		SolicitacaoAnalise solicitanteAnalise = new SolicitacaoAnalise(newProp.getDocumento(),
				newProp.getNome(),newProp.getId());

		SolicitacaoResponse solicitacaoResponse = propostaClient.solicitarDados(solicitanteAnalise);

		newProp.alocarDadosFinanceiros(solicitacaoResponse.getResultadoSolicitacao());

		propostaRepository.save(newProp);

		URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(newProp.getId()).toUri();

		return ResponseEntity.created(uri).body(propostaDto);
	}

}
