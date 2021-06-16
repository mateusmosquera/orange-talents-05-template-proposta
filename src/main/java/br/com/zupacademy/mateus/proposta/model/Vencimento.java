package br.com.zupacademy.mateus.proposta.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vencimento {
	@Id
	private String id;
	
	private int quantidade;
	
	private BigDecimal valor;
	
	private String dataDeCriacao;

	@Deprecated
	public Vencimento(){

	}

	public Vencimento(String id, int quantidade, BigDecimal valor, String dataDeCriacao) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
	}

	public String getId() {
		return id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDataDeCriacao() {
		return dataDeCriacao;
	}
}
