package br.com.zupacademy.mateus.proposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AvisoViagem {
	@Id
	private Long id;
	
	private String validoAte;
	
	private	String destino;

	public AvisoViagem(Long id, String validoAte, String destino) {
		this.id = id;
		this.validoAte = validoAte;
		this.destino = destino;
	}

	public Long getId() {
		return id;
	}

	public String getValidoAte() {
		return validoAte;
	}

	public String getDestino() {
		return destino;
	}
	
}
