package br.ufes.scap.nucleo.dominio;

import java.io.Serializable;

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
@Table(name = "parentesco")
public class Parentesco implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_parentesco")
	private Long id_parentesco;	
	
	@ManyToOne
	@JoinColumn(name="id_pessoa1")
	@NotNull
	private Pessoa pessoa1;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa2")
	@NotNull
	private Pessoa pessoa2;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoParentesco tipoParentesco;

	public Long getId_parentesco() {
		return id_parentesco;
	}

	public void setId_parentesco(Long id_parentesco) {
		this.id_parentesco = id_parentesco;
	}

	public Pessoa getPessoa1() {
		return pessoa1;
	}

	public void setPessoa1(Pessoa pessoa1) {
		this.pessoa1 = pessoa1;
	}

	public Pessoa getPessoa2() {
		return pessoa2;
	}

	public void setPessoa2(Pessoa pessoa2) {
		this.pessoa2 = pessoa2;
	}

	public TipoParentesco getTipoParentesco() {
		return tipoParentesco;
	}

	public void setTipoParentesco(TipoParentesco tipoParentesco) {
		this.tipoParentesco = tipoParentesco;
	}

}