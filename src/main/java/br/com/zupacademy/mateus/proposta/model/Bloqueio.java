package br.com.zupacademy.mateus.proposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bloqueio {
	@Id
	private String id;
	
	private String bloqueadoEm;
	
	private String sistemaResponsavel;

	public Bloqueio(String id, String bloqueadoEm, String sistemaResponsavel) {
		super();
		this.id = id;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getId() {
		return id;
	}

	public String getBloqueadoEm() {
		return bloqueadoEm;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
}
