package br.com.zupacademy.mateus.proposta.config;

import java.util.Collection;

public class ErroPadronizado {
	
	private Collection<String> mensagens;

	public ErroPadronizado(Collection<String> mensagens2) {
		super();
		this.mensagens = mensagens2;
	}

	public Collection<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(Collection<String> mensagens) {
		this.mensagens = mensagens;
	}
	

}
