package br.ufes.scap.nucleo.dominio;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parecer")
public class Parecer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id_parecer;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	@NotNull
	private Pessoa relator;
	
	@ManyToOne
	@JoinColumn(name = "id_afastamento")
	@NotNull
	private Afastamento afastamento;
	
	@Column(name= "data_parecer")
	private Calendar data_parecer;
	
	@Enumerated(EnumType.STRING)
	private TipoParecer julgamento;
	
	@Column
	private String motivoIndeferimento;

	public Long getId_parecer() {
		return id_parecer;
	}

	public void setId_parecer(Long id_parecer) {
		this.id_parecer = id_parecer;
	}

	public Pessoa getRelator() {
		return relator;
	}

	public void setRelator(Pessoa relator) {
		this.relator = relator;
	}

	public Afastamento getAfastamento() {
		return afastamento;
	}

	public void setAfastamento(Afastamento afastamento) {
		this.afastamento = afastamento;
	}

	public Calendar getData_parecer() {
		return data_parecer;
	}

	public void setData_parecer(Calendar data_parecer) {
		this.data_parecer = data_parecer;
	}

	public TipoParecer getJulgamento() {
		return julgamento;
	}

	public void setJulgamento(TipoParecer julgamento) {
		this.julgamento = julgamento;
	}

	public String getMotivoIndeferimento() {
		return motivoIndeferimento;
	}

	public void setMotivoIndeferimento(String motivoIndeferimento) {
		this.motivoIndeferimento = motivoIndeferimento;
	}
}