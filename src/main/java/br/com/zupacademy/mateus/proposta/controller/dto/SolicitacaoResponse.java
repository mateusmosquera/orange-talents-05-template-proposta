package br.com.zupacademy.mateus.proposta.controller.dto;

public class SolicitacaoResponse {
	
	private String documento;
	
	private String nome;
	
	private String resultadoSolicitacao;
	
	private String idProposta;
	
	@Deprecated
	public SolicitacaoResponse() {
		
	}

	public SolicitacaoResponse(String documento, String nome, String resultadoSolicitacao, String idProposta) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
}
