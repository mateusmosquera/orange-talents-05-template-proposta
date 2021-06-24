package br.com.zupacademy.mateus.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class CarteiraDigital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String email;

	private LocalDateTime associadaEm;
	@NotBlank
	private String emissor;

	@ManyToOne
	@JoinColumn(name="cartao_id", nullable=false)
	private Cartao cartao;

	@Deprecated
	public CarteiraDigital(){

	}

	public CarteiraDigital(String email, String emissor,Cartao cartao) {
		super();
		this.email = email;
		this.associadaEm = LocalDateTime.now();
		this.emissor = emissor;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}
}
