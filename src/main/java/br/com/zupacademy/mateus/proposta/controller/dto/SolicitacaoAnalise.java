package br.com.zupacademy.mateus.proposta.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.zupacademy.mateus.proposta.model.Usuario;

public class SolicitacaoAnalise{

	@CPF
	@NotBlank
	private String documento;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Long idProposta;

	public SolicitacaoAnalise(@CPF @NotBlank String documento, @NotBlank String nome, @NotNull Long idProposta) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(Long idProposta) {
		this.idProposta = idProposta;
	}

}
