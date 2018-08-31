package br.ufes.scap.nucleo.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="pessoa")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPessoa", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("10")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="id_pessoa")
	private Long id_pessoa;
	
	@NotNull
	@Column
	@Size(max = 144)
	private String nome;
	
	@NotNull
	@Column
	@Size(max = 144)
	private String sobreNome;
	
	@NotNull
	@Column
	@Size(max = 144)
	private String email;
	
	@NotNull
	@Column
	@Size(max = 144)
	private String telefone;
	
	@NotNull
	@Column
	@Size(max = 144)
	private String matricula;
	
	@NotNull
	@Column(name="tipoPessoa", nullable=false, updatable=false, insertable=false)
	private String tipoPessoa;
	
	@Size(max = 144)
	@NotNull
	@Column(name="password")
	private String password;
	
	public Long getId() {
		return id_pessoa;
	}

	public void setId(Long id) {
		id_pessoa = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Pessoa(){
    }
	
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Long id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	
}