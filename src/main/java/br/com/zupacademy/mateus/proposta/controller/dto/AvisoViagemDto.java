package br.com.zupacademy.mateus.proposta.controller.dto;

import br.com.zupacademy.mateus.proposta.model.AvisoViagem;
import br.com.zupacademy.mateus.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AvisoViagemDto {

    @Future
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate validoAte;
    @NotBlank
    private	String destino;

    public AvisoViagemDto(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagem toModel(String ipCLient, String userAgent, Cartao cartao) {
        return new AvisoViagem( this.validoAte, this.destino, ipCLient, userAgent,cartao);
    }
}
