package br.ufes.scap.nucleo.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "afastamento")
public class Afastamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_afastamento")
	private Long id_afastamento;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa solicitante;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private SituacaoSolic situacaoSolicitacao;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoAfastamento tipoAfastamento;
	
	@Enumerated(EnumType.STRING)
	private Onus onus;
	
	@Column(name="data_criacao")
	@NotNull
	private Calendar data_criacao;
	
	@Column(name="data_iniAfast")
	@NotNull
	private Calendar data_iniAfast;
	
	@Column(name="data_fimAfast") 
	@NotNull
	private Calendar data_fimAfast;
	
	@Column(name="data_inievento")
	@NotNull
	private Calendar data_inievento;
	
	@Column(name="data_fimevento")
	@NotNull
	private Calendar data_fimevento;
	
	@Size(max = 144)
	@Column(name="nome_evento")
	@NotNull
	private String nome_evento;
	
	@Size(max = 144)
	@Column(name="nome_cidade")
	@NotNull
	private String nome_cidade;
	
	@Size(max = 255)
	@Column(name="motivo_afast")
	private String motivo_afast;
	
	@Size(max = 255)
	@Column(name="motivo_cancel")
	private String motivo_cancel;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "afastamento")
	private Set<Documento> documentos;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "afastamento")
	private Set<Parecer> pareceres;
	
	public Pessoa getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Pessoa solicitante) {
		this.solicitante = solicitante;
	}
	
	public TipoAfastamento getTipoAfastamento() {
		return tipoAfastamento;
	}

	public void setTipoAfastamento(TipoAfastamento tipoAfastamento) {
		this.tipoAfastamento = tipoAfastamento;
	}

	public Onus getOnus() {
		return onus;
	}

	public void setOnus(Onus onus) {
		this.onus = onus;
	}

	public Calendar getData_iniAfast() {
		return data_iniAfast;
	}

	public void setData_iniAfast(Calendar data_iniAfast) {
		this.data_iniAfast = data_iniAfast;
	}

	public Calendar getData_fimAfast() {
		return data_fimAfast;
	}

	public void setData_fimAfast(Calendar data_fimAfast) {
		this.data_fimAfast = data_fimAfast;
	}

	public Calendar getData_iniEvento() {
		return data_inievento;
	}
	
	public void setData_criacao(Calendar data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Calendar getData_criacao() {
		return data_criacao;
	}

	public void setData_iniEvento(Calendar data_inievento) {
		this.data_inievento = data_inievento;
	}

	public Calendar getData_fimEvento() {
		return data_fimevento;
	}

	public void setData_fimEvento(Calendar data_fimevento) {
		this.data_fimevento = data_fimevento;
	}

	public String getNome_evento() {
		return nome_evento;
	}

	public void setNome_evento(String nome_evento) {
		this.nome_evento = nome_evento;
	}

	public String getNome_cidade() {
		return nome_cidade;
	}

	public void setNome_cidade(String nome_cidade) {
		this.nome_cidade = nome_cidade;
	}

	public String getMotivo_afast() {
		return motivo_afast;
	}

	public void setMotivo_afast(String motivo_afast) {
		this.motivo_afast = motivo_afast;
	}

	public String getMotivo_cancel() {
		return motivo_cancel;
	}

	public void setMotivo_cancel(String motivo_cancel) {
		this.motivo_cancel = motivo_cancel;
	}

	public Long getId_afastamento() {
		return id_afastamento;
	}

	public void setId_afastamento(Long id_afastamento) {
		this.id_afastamento = id_afastamento;
	}

	public SituacaoSolic getSituacaoSolicitacao() {
		return situacaoSolicitacao;
	}

	public void setSituacaoSolicitacao(SituacaoSolic situacaoSolicitacao) {
		this.situacaoSolicitacao = situacaoSolicitacao;
	}
	
	public Afastamento(){
    }
	
}