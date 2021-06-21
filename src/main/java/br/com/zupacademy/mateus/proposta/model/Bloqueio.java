package br.com.zupacademy.mateus.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Bloqueio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String bloqueadoEm;
	@NotBlank
	private String sistemaResponsavel;
	@NotBlank
	private String ipCLient;
	@NotBlank
	private String userAgent;

	@Deprecated
	public Bloqueio(){

	}

	public Bloqueio(String bloqueadoEm, String sistemaResponsavel, String ipCLient, String userAgent) {
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ipCLient = ipCLient;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}

	public String getBloqueadoEm() {
		return bloqueadoEm;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public String getIpCLient() {
		return ipCLient;
	}

	public String getUserAgent() {
		return userAgent;
	}
}
