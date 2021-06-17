package br.com.zupacademy.mateus.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String code;

    @NotNull
    @PastOrPresent
    private LocalDateTime cadastroInstante;

    @Deprecated
    public Biometria(){}


    public Biometria(String code) {
        this.code = code;
        this.cadastroInstante = LocalDateTime.now();
    }
}
