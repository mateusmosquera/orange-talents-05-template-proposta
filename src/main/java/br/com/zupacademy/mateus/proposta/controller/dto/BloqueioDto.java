package br.com.zupacademy.mateus.proposta.controller.dto;

import br.com.zupacademy.mateus.proposta.model.Bloqueio;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class BloqueioDto {


    private String bloqueadoEm;
    @NotBlank
    private String sistemaResponsavel;

    @JsonCreator
    public BloqueioDto(@JsonProperty("sistemaResponsavel")String sistemaResponsavel) {
        this.bloqueadoEm = String.valueOf(LocalDateTime.now());
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public Bloqueio toModel(String ipAddress, String userAgent) {

        return new Bloqueio(this.bloqueadoEm,this.sistemaResponsavel,ipAddress,userAgent);
    }
}
