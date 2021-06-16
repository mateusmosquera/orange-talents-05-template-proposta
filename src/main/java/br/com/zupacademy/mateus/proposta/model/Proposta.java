package br.com.zupacademy.mateus.proposta.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.mateus.proposta.config.exceptions.ApiErroException;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoAnalise;
import br.com.zupacademy.mateus.proposta.controller.dto.SolicitacaoResponse;

@Entity
public class Proposta {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	private SituacaoFinanceira situacaoFinanceira;
	
	private String idCartao;
	
	@Deprecated
	public Proposta() {
		
	}

	public Proposta(@CPF @NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,@NotBlank String endereco,
			@NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}
	
	public BigDecimal getSalario() {
		return salario;
	}
	
	public SituacaoFinanceira getSituacaoFinanceira() {
		return situacaoFinanceira;
	}

	public void setIdCartao(String idCartao) {
		this.idCartao = idCartao;
	}

	public void alocarDadosFinanceiros(String resultadoSolicitacao) {
		if(resultadoSolicitacao.equals("COM_RESTRICAO") ) {
			this.situacaoFinanceira = SituacaoFinanceira.NAO_ELEGIVEL;
		}
		else if(resultadoSolicitacao.equals("SEM_RESTRICAO") ) {
			this.situacaoFinanceira = SituacaoFinanceira.ELEGIVEL;
		}else {
			throw new ApiErroException(HttpStatus.PRECONDITION_FAILED,"Resultado não esperado: "+ resultadoSolicitacao);
		}
	}

	public void associarCartao(RestTemplate restTemplate) {
		String url = "http://localhost:8888/api/cartoes?idProposta="+this.id;

		Cartao cartaoAssociado = restTemplate.getForObject(url,Cartao.class);

		this.idCartao = cartaoAssociado.getId();

	}

}