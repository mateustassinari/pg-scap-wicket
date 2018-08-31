package br.ufes.scap.nucleo.controle;

import java.io.Serializable;

public class ParecerLista  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nomeCriador;
	private String julgamento;
	private String data;
	private String motivo;
	
	public ParecerLista(){
	}
	
	public String getNomeCriador() {
		return nomeCriador;
	}
	
	public void setNomeCriador(String nomeCriador) {
		this.nomeCriador = nomeCriador;
	}
	
	public String getJulgamento() {
		return julgamento;
	}
	
	public void setJulgamento(String julgamento) {
		this.julgamento = julgamento;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getMotivo() {
		return motivo;
	}
	
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}