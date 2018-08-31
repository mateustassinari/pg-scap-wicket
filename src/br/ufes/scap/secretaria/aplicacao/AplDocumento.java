package br.ufes.scap.secretaria.aplicacao;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Documento;

public interface AplDocumento {
	
	public void salvar(Documento documento);
	
	public Documento buscaId(String id_documento);
	
	public List<Documento> buscaPorAfastamento(String id_afastamento);
	
}