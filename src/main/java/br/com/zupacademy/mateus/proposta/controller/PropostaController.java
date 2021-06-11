package br.com.zupacademy.mateus.proposta.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;
import br.com.zupacademy.mateus.proposta.controller.dto.PropostaDto;
import br.com.zupacademy.mateus.proposta.model.Proposta;
import br.com.zupacademy.mateus.proposta.repository.PropostaRepository;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaDto> cadastrar(@RequestBody @Valid PropostaDto propostaDto, UriComponentsBuilder uriBuilder) {
		
		if(!propostaDto.documentoIsUnique(propostaRepository)) {
			logger.warn("Proposta já tem documento cadastrado! documento={}",propostaDto.getDocumento());
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,"Documento já cadsatrado");
		}
		
		Proposta newProposta = propostaDto.toModel();
		
		propostaRepository.save(newProposta);
		
		URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(newProposta.getId()).toUri();
		
		logger.info("Nova Proposta documento={} salario={} criada com sucesso!", newProposta.getDocumento(),newProposta.getSalario());
		
		return ResponseEntity.created(uri).body(propostaDto);
	}
}
