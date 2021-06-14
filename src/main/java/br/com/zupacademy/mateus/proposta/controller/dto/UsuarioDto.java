package br.com.zupacademy.mateus.proposta.controller.dto;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.br.CPF;

import br.com.zupacademy.mateus.proposta.model.Usuario;
import br.com.zupacademy.mateus.proposta.repository.UsuarioRepository;

public class UsuarioDto {
	
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
	
	

	public UsuarioDto(@CPF @NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Usuario toModel() {
		
		return new Usuario(this.documento, this.email,this.nome, this.endereco, this.salario);
	}

	public boolean documentoIsUnique(UsuarioRepository UsuarioRepository) {
		Optional<Usuario> user = UsuarioRepository.findByDocumento(documento);
		if(user.isPresent()) {
			return false;
		}
		return true;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
}
