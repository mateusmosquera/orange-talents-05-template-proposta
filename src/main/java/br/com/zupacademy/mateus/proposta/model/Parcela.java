package br.com.zupacademy.mateus.proposta.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parcela {
	@Id
	private String id;
	
	private int quantidade;
	
	private BigDecimal valor;

	public Parcela(String id, int quantidade, BigDecimal valor) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	
}
