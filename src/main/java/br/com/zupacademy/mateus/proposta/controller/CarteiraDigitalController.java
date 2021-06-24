package br.com.zupacademy.mateus.proposta.controller;

import br.com.zupacademy.mateus.proposta.Client.CartoesClient;
import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;
import br.com.zupacademy.mateus.proposta.controller.dto.CarteiraDigitalDto;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoInclusaoCarteira;
import br.com.zupacademy.mateus.proposta.model.AssociacaoCarteira;
import br.com.zupacademy.mateus.proposta.model.Cartao;
import br.com.zupacademy.mateus.proposta.model.CarteiraDigital;
import br.com.zupacademy.mateus.proposta.repository.CartaoRepository;
import br.com.zupacademy.mateus.proposta.repository.CarteiraDigitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/carteira")
public class CarteiraDigitalController {
    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraDigitalRepository carteiraDigitalRepository;

    @Autowired
    private CartoesClient cartoesClient;

    private final Logger logger = LoggerFactory.getLogger(CarteiraDigitalController.class);

    @PostMapping
    public ResponseEntity<?> incluirCarteira(@PathParam("id") String id, @RequestBody @Valid CarteiraDigitalDto carteiraDigitalDto ){

        if(!verificarAssociacaoCartao(carteiraDigitalDto.getEmissor())){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Não foi encontrada está relação de carteira digital");
        }

        Optional<Cartao> optCartao = cartaoRepository.findById(id);

        if(optCartao.isEmpty()){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Não foi encontrado o cartão");
        }

        Optional<CarteiraDigital> optCarteira = carteiraDigitalRepository.findByEmissorAndCartaoId(carteiraDigitalDto.getEmissor(),id);
        if(optCarteira.isPresent()){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já tem carteira da emissora cadastrada");
        }

        CarteiraDigital carteiraDigital = carteiraDigitalDto.toModel(optCartao.get());

        optCartao.get().getCarteiras().add(carteiraDigital);


        cartaoRepository.save(optCartao.get());
        carteiraDigitalRepository.save(carteiraDigital);

        SolicitacaoInclusaoCarteira solicitacaoInclusaoCarteira =
                new SolicitacaoInclusaoCarteira(carteiraDigital.getEmail(), carteiraDigital.getEmissor());
        try {
            cartoesClient.incluirCarteira(id,solicitacaoInclusaoCarteira);
        }catch (RestClientException e){
            logger.error(e.getMessage());
            throw e;
        }
        return ResponseEntity.ok().build();
    }

    public boolean verificarAssociacaoCartao(String carteira){
        for(AssociacaoCarteira a : AssociacaoCarteira.values()){
            if (a.name().equals(carteira)){
                return true;
            }
        }
        return false;
    }

}
