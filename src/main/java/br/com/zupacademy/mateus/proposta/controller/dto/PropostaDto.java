package br.com.zupacademy.mateus.proposta.controller.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.br.CPF;

import br.com.zupacademy.mateus.proposta.model.Proposta;

public class PropostaDto {
	
	@CPF
	@NotBlank
	private String documento;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String endereco;
	
	@NotNull
	@Positive
	private BigDecimal salario;

	public PropostaDto(@CPF @NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta toModel() {
		
		return new Proposta(this.documento, this.email,this.nome, this.endereco, this.salario);
	}
	
	
}
