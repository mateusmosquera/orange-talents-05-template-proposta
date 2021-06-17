package br.com.zupacademy.mateus.proposta.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity	
public class Cartao {

	@Id
	@NotBlank
	private String id;
	@NotNull @NotBlank
	private String emitidoEm;
	@NotNull @NotBlank
	private String titular;
	@OneToMany
	private List<Bloqueio> bloqueios;
	@OneToMany
	private List<AvisoViagem> avisos;
	@OneToMany
	private List<CarteiraDigital> carteiras;
	@OneToMany
	private List<Parcela> parcelas;
	
	private int limite;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "renegociacao_id", referencedColumnName = "id")
	private Renegociacao renegociacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vencimento_id", referencedColumnName = "id")
	private Vencimento vencimento;
	@NotNull
	private long idProposta;
	@OneToMany
	private Set<Biometria> biometria;

	@Deprecated
	public Cartao(){
	}

	public Cartao(@NotBlank String id, @NotBlank String emitidoEm, @NotBlank String titular, List<Bloqueio> bloqueios,
			List<AvisoViagem> avisos, List<CarteiraDigital> carteiras, List<Parcela> parcelas, int limite,
			Renegociacao renegociao, Vencimento vencimento, long idProposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
		this.limite = limite;
		this.renegociacao = renegociao;
		this.vencimento = vencimento;
		this.idProposta = idProposta;
	}

	public String getId() {
		return id;
	}

	public String getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public List<Bloqueio> getBloqueios() {
		return bloqueios;
	}

	public List<AvisoViagem> getAvisos() {
		return avisos;
	}

	public List<CarteiraDigital> getCarteiras() {
		return carteiras;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public int getLimite() {
		return limite;
	}

	public Renegociacao getRenegociao() {
		return renegociacao;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

	public long getIdProposta() {
		return idProposta;
	}

	public Set<Biometria> getBiometria() {
		return biometria;
	}

}


