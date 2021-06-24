package br.com.zupacademy.mateus.proposta.controller.dto;

import br.com.zupacademy.mateus.proposta.model.Cartao;
import br.com.zupacademy.mateus.proposta.model.CarteiraDigital;

import javax.validation.constraints.NotBlank;

public class CarteiraDigitalDto {

    @NotBlank
    private String email;
    @NotBlank
    private String emissor;

    public CarteiraDigitalDto(String email, String emissor) {
        this.email = email;
        this.emissor = emissor;
    }

    public String getEmail() {
        return email;
    }

    public String getEmissor() {
        return emissor;
    }

    public CarteiraDigital toModel(Cartao cartao) {
        return new CarteiraDigital(this.email,this.emissor,cartao);
    }
}
