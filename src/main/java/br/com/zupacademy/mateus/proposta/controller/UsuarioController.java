package br.com.zupacademy.mateus.proposta.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoAnalise;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoResponse;
import br.com.zupacademy.mateus.proposta.controller.dto.UsuarioDto;
import br.com.zupacademy.mateus.proposta.model.Usuario;
import br.com.zupacademy.mateus.proposta.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	RestTemplate restTemplate = new RestTemplate();
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto, UriComponentsBuilder uriBuilder) {
		
		if(!usuarioDto.documentoIsUnique(usuarioRepository)) {
			logger.warn("Usuario já tem documento cadastrado! documento={}",usuarioDto.getDocumento());
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,"Documento já cadsatrado");
		}
		
		Usuario newUser = usuarioDto.toModel();
		
		newUser.alocarDadosFinanceiros(restTemplate);
		
		usuarioRepository.save(newUser);
		
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(newUser.getId()).toUri();
		
		logger.info("Nova Usuario documento={} salario={} criada com sucesso!", newUser.getDocumento(),newUser.getSalario());
		
		return ResponseEntity.created(uri).body(usuarioDto);
	}
}

//@GetMapping
//public ResponseEntity<?> consultarDadosFinanceiros(@RequestBody @Valid SolicitacaoAnalise solicitanteAnalise){
//	
//	String url = "http://localhost:9999/api/solicitacao";
//	
//	SolicitacaoResponse solicResponse = restTemplate.postForObject(url, solicitanteAnalise, SolicitacaoResponse.class);	
//	
//	
//	if(solicResponse.getResultadoSolicitacao().equals("COM_RESTRICAO") ) {
//		return ResponseEntity.ok("NAO_ELEGIVEL");
//	}
//	else if(solicResponse.getResultadoSolicitacao().equals("SEM_RESTRICAO") ) {
//	return ResponseEntity.ok("ELEGIVEL");
//	}
//	throw new ApiErroException(HttpStatus.PRECONDITION_FAILED,"Resultado não esperado: "+ solicResponse.getResultadoSolicitacao());
//}
