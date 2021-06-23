package br.com.zupacademy.mateus.proposta.model;

import java.util.ArrayList;
import java.util.Collection;
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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bloqueio_id", referencedColumnName = "id")
	private Bloqueio bloqueio;

	@OneToMany(mappedBy = "cartao")
	private Collection<AvisoViagem> avisos = new ArrayList<>();
	@OneToMany(mappedBy = "cartao")
	private Collection<CarteiraDigital> carteiras = new ArrayList<>();
	@OneToMany(mappedBy = "cartao")
	private Collection<Parcela> parcelas = new ArrayList<>();
	
	private int limite;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "renegociacao_id", referencedColumnName = "id")
	private Renegociacao renegociacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vencimento_id", referencedColumnName = "id")
	private Vencimento vencimento;
	@NotNull
	private long idProposta;
	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometria;

	@Deprecated
	public Cartao(){
	}

	public Cartao(@NotBlank String id, @NotBlank String emitidoEm, @NotBlank String titular, Bloqueio bloqueio,
			List<AvisoViagem> avisos, List<CarteiraDigital> carteiras, List<Parcela> parcelas, int limite,
			Renegociacao renegociao, Vencimento vencimento, long idProposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.bloqueio = bloqueio;
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

	public Bloqueio getBloqueios() {
		return bloqueio;
	}

	public Collection<AvisoViagem> getAvisos() {
		return avisos;
	}

	public Collection<CarteiraDigital> getCarteiras() {
		return carteiras;
	}

	public Collection<Parcela> getParcelas() {
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

	public void setBloqueio(Bloqueio bloqueio) {
		this.bloqueio = bloqueio;
	}

}


