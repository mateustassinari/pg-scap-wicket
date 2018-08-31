package br.ufes.scap.nucleo.controle;

import java.io.Serializable;

public class AfastamentoLista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id_afastamento;
	private String nome_pessoa;
	private String nome_evento;
	private String nome_cidade;
	private String tipoAfastamento;
	private String situacaoSolicitacao;
	private String data_iniAfast;
	private String data_fimAfast;
	
	public AfastamentoLista(){
    }

	public String getId_afastamento() {
		return this.id_afastamento;
	}

	public void setId_afastamento(String id_afastamento) {
		this.id_afastamento = id_afastamento;
	}

	public String getNome_pessoa() {
		return this.nome_pessoa;
	}

	public void setNome_pessoa(String nome_pessoa) {
		this.nome_pessoa = nome_pessoa;
	}

	public String getNome_evento() {
		return this.nome_evento;
	}

	public void setNome_evento(String nome_evento) {
		this.nome_evento = nome_evento;
	}

	public String getNome_cidade() {
		return this.nome_cidade;
	}

	public void setNome_cidade(String nome_cidade) {
		this.nome_cidade = nome_cidade;
	}

	public String getTipoAfastamento() {
		return this.tipoAfastamento;
	}

	public void setTipoAfastamento(String tipoAfastamento) {
		this.tipoAfastamento = tipoAfastamento;
	}

	public String getSituacaoSolicitacao() {
		return this.situacaoSolicitacao;
	}

	public void setSituacaoSolicitacao(String situacaoSolicitacao) {
		this.situacaoSolicitacao = situacaoSolicitacao;
	}

	public String getData_iniAfast() {
		return this.data_iniAfast;
	}

	public void setData_iniAfast(String data_iniAfast) {
		this.data_iniAfast = data_iniAfast;
	}

	public String getData_fimAfast() {
		return this.data_fimAfast;
	}

	public void setData_fimAfast(String data_fimAfast) {
		this.data_fimAfast = data_fimAfast;
	}
	
}