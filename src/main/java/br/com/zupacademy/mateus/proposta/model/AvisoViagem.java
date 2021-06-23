package br.com.zupacademy.mateus.proposta.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Future
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate validoAte;
	@NotBlank
	private	String destino;

	private LocalDateTime cadastroEm;
	@NotBlank
	private String ipCLient;

	private String userAgent;

	@ManyToOne
	@JoinColumn(name="cartao_id", nullable=false)
	private Cartao cartao;

	@Deprecated
	public AvisoViagem(){}

	public AvisoViagem(LocalDate validoAte, String destino, String ipCLient, String userAgent,Cartao cartao) {
		this.validoAte = validoAte;
		this.destino = destino;
		this.cadastroEm = LocalDateTime.now();;
		this.ipCLient = ipCLient;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDateTime getCadastroEm() {
		return cadastroEm;
	}

	public String getIpCLient() {
		return ipCLient;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
}
