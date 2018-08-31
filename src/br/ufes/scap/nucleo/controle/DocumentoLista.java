package br.ufes.scap.nucleo.controle;

import java.io.Serializable;

public class DocumentoLista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String titulo;
	private String arquivo;
	private String juntada;
	private byte[] content;
	
	public DocumentoLista(){	
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	public String getJuntada() {
		return juntada;
	}
	
	public void setJuntada(String juntada) {
		this.juntada = juntada;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}