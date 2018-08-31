package br.ufes.scap.nucleo.dominio;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "documento")
public class Documento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_documento")
	private Long id_documento;
	
	@NotNull
	@Column
	@Size(max = 144)
	private String tituloDocumento;
	
	@NotNull
	@Column
	@Size(max = 144)
	private String nomeArquivo;
	
	@ManyToOne
	@JoinColumn(name="id_afastamento")
	private Afastamento afastamento;
	
	@Column(name="data_juntada")
	@NotNull
	private Calendar data_juntada;
	
	@Lob
	@Column
    private byte[] content;

	public Long getId_documento() {
		return id_documento;
	}

	public void setId_documento(Long id_documento) {
		this.id_documento = id_documento;
	}

	public String getTituloDocumento() {
		return tituloDocumento;
	}

	public void setTituloDocumento(String tituloDocumento) {
		this.tituloDocumento = tituloDocumento;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Afastamento getAfastamento() {
		return afastamento;
	}

	public void setAfastamento(Afastamento afastamento) {
		this.afastamento = afastamento;
	}

	public Calendar getData_juntada() {
		return data_juntada;
	}

	public void setData_juntada(Calendar data_juntada) {
		this.data_juntada = data_juntada;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}