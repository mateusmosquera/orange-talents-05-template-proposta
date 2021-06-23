package br.com.zupacademy.mateus.proposta.controller.dto;

import br.com.zupacademy.mateus.proposta.model.AvisoViagem;
import br.com.zupacademy.mateus.proposta.model.Cartao;

import javax.validation.constraints.NotBlank;

public class AvisoViagemDto {

    @NotBlank
    private String validoAte;
    @NotBlank
    private	String destino;

    public AvisoViagemDto(String validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagem toModel(String ipCLient, String userAgent, Cartao cartao) {
        return new AvisoViagem( this.validoAte, this.destino, ipCLient, userAgent,cartao);
    }
}
