package br.com.zupacademy.mateus.proposta.model;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name="cartao_id", nullable=false)
    private Cartao cartao;

    @Deprecated
    public Biometria(){}


    public Biometria(String code) {
        this.code = code;
        this.cadastroInstante = LocalDateTime.now();
    }
}
