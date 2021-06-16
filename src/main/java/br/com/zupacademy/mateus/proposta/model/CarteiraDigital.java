package br.com.zupacademy.mateus.proposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CarteiraDigital {
	@Id
	private String id;
	
	private String email;
	
	private String associadaEm;
	
	private String emissaor;

	public CarteiraDigital(String id, String email, String associadaEm, String emissaor) {
		super();
		this.id = id;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissaor = emissaor;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissaor() {
		return emissaor;
	}
}
