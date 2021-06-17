package br.com.zupacademy.mateus.proposta.controller;

import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;
import br.com.zupacademy.mateus.proposta.controller.dto.BiometriaDto;
import br.com.zupacademy.mateus.proposta.model.Biometria;
import br.com.zupacademy.mateus.proposta.model.Cartao;
import br.com.zupacademy.mateus.proposta.repository.BiometriaRepository;
import br.com.zupacademy.mateus.proposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cartao")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @PostMapping
    @RequestMapping("/biometria")
    public ResponseEntity<?> cadastrarBiometria(@PathParam("id") String id, @RequestBody @Valid BiometriaDto biometriaDto,
                                                UriComponentsBuilder uriBuilder){

        Optional<Cartao> optCartao = cartaoRepository.findById(id);

        if(!optCartao.isPresent()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Não foi encontrado o cartão");
        }

        Biometria biometria = biometriaDto.toModel();

        optCartao.get().getBiometria().add(biometria);

        biometriaRepository.save(biometria);
        cartaoRepository.save(optCartao.get());

        URI uri = uriBuilder.path("/cartao/{id}").buildAndExpand(optCartao.get()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
