package br.com.zupacademy.mateus.proposta.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Parcela {
	@Id
	private String id;
	
	private int quantidade;
	
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name="cartao_id", nullable=false)
	private Cartao cartao;

	public Parcela(String id, int quantidade, BigDecimal valor) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	
}
