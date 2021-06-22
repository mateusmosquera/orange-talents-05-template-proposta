package br.com.zupacademy.mateus.proposta.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitacaoBloqueio {

    private String sistemaResponsavel;

    @JsonCreator
    public SolicitacaoBloqueio(@JsonProperty("sistemaResponsavel")String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
