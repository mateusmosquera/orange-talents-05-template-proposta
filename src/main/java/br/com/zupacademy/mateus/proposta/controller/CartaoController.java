package br.com.zupacademy.mateus.proposta.controller;

import br.com.zupacademy.mateus.proposta.Client.CartoesClient;
import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;
import br.com.zupacademy.mateus.proposta.controller.dto.BiometriaDto;
import br.com.zupacademy.mateus.proposta.controller.dto.BloqueioDto;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoBloqueio;
import br.com.zupacademy.mateus.proposta.model.Biometria;
import br.com.zupacademy.mateus.proposta.model.Bloqueio;
import br.com.zupacademy.mateus.proposta.model.Cartao;
import br.com.zupacademy.mateus.proposta.repository.BiometriaRepository;
import br.com.zupacademy.mateus.proposta.repository.BloqueioRepository;
import br.com.zupacademy.mateus.proposta.repository.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private BloqueioRepository bloqueioRepository;
    @Autowired
    private CartoesClient cartoesClient;

    private final Logger logger = LoggerFactory.getLogger(CartaoController.class);

    @PostMapping
    @RequestMapping("/biometria")
    public ResponseEntity<?> cadastrarBiometria(@PathParam("id") String id, @RequestBody @Valid BiometriaDto biometriaDto,
                                                UriComponentsBuilder uriBuilder){

        Optional<Cartao> optCartao = cartaoRepository.findById(id);

        if(optCartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Não foi encontrado o cartão");
        }

        Biometria biometria = biometriaDto.toModel();

        optCartao.get().getBiometria().add(biometria);

        biometriaRepository.save(biometria);
        cartaoRepository.save(optCartao.get());

        URI uri = uriBuilder.path("/cartao/{id}").buildAndExpand(optCartao.get()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping
    @RequestMapping("/bloqueio")
    public ResponseEntity<?> bloquear(@PathParam("id") String id, @RequestHeader(value = "User-Agent") String userAgent, @RequestBody @Valid BloqueioDto bloqueiodto,
                                      UriComponentsBuilder uriBuilder, HttpServletRequest request){
        Optional<Cartao> optCartao = cartaoRepository.findById(id);

        if(optCartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Não foi encontrado o cartão");
        }
        if(optCartao.get().getBloqueios() != null){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já tem bloqueio");
        }

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        Bloqueio bloqueio = bloqueiodto.toModel(ipAddress,userAgent);

        optCartao.get().setBloqueio(bloqueio);

        bloqueioRepository.save(bloqueio);
        cartaoRepository.save(optCartao.get());


        URI uri = uriBuilder.path("/cartao/{id}").buildAndExpand(optCartao.get()).toUri();

        SolicitacaoBloqueio solicitacaoBloqueio = new SolicitacaoBloqueio(bloqueio.getSistemaResponsavel());

        try {
            cartoesClient.bloquear(optCartao.get().getId(), solicitacaoBloqueio);
        }catch (RestClientException e){
            logger.error(e.getMessage());
            throw e;
        }

        return ResponseEntity.created(uri).build();
    }
}
