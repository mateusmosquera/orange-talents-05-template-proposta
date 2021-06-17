package br.com.zupacademy.mateus.proposta.controller.dto;


import br.com.zupacademy.mateus.proposta.model.Biometria;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BiometriaDto {

    @NotNull @NotBlank
    private String biometria;

    @Deprecated
    public BiometriaDto(){

    }

    @JsonCreator
    public BiometriaDto(@JsonProperty("biometria") String biometria) {
        this.biometria = biometria;
    }

    public Biometria toModel() {
        byte[] decode = Base64.getDecoder().decode(this.biometria.getBytes(StandardCharsets.UTF_8));
        String mensagemDecodificada = new String(decode);
        return new Biometria(mensagemDecodificada);
    }
}
