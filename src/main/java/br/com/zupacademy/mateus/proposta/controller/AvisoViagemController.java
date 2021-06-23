package br.com.zupacademy.mateus.proposta.controller;

import br.com.zupacademy.mateus.proposta.Client.CartoesClient;
import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;
import br.com.zupacademy.mateus.proposta.controller.dto.AvisoViagemDto;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoAvisoViagem;
import br.com.zupacademy.mateus.proposta.model.AvisoViagem;
import br.com.zupacademy.mateus.proposta.model.Cartao;
import br.com.zupacademy.mateus.proposta.repository.AvisoViagemRepository;
import br.com.zupacademy.mateus.proposta.repository.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/viagem")
public class AvisoViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private CartoesClient cartoesClient;

    private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);


    @PostMapping
    public ResponseEntity<?> cadastrar(@PathParam("id") String id, @RequestHeader(value = "User-Agent") String userAgent,
                                       @RequestBody @Valid AvisoViagemDto avisoViagemDto, HttpServletRequest request){
        Optional<Cartao> optCartao = cartaoRepository.findById(id);

        if(optCartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Não foi encontrado o cartão");
        }

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        AvisoViagem avisoViagem = avisoViagemDto.toModel(ipAddress,userAgent,optCartao.get());

        optCartao.get().getAvisos().add(avisoViagem);


        cartaoRepository.save(optCartao.get());
        avisoViagemRepository.save(avisoViagem);

        SolicitacaoAvisoViagem solicitacaoAvisoViagem = new SolicitacaoAvisoViagem(avisoViagem.getDestino(),
                                                                                    avisoViagem.getValidoAte());
        try {
            cartoesClient.avisoViagem(optCartao.get().getId(), solicitacaoAvisoViagem);
        }catch (RestClientException e){
            logger.error(e.getMessage());
            throw e;
        }
        return ResponseEntity.ok().build();
    }

}
