package br.com.zupacademy.mateus.proposta.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class SolicitacaoAvisoViagem {
    @NotBlank
    private String destino;

    @Future
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate validoAte;

    public SolicitacaoAvisoViagem(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
