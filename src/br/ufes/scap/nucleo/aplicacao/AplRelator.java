package br.ufes.scap.nucleo.aplicacao;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Relator;

public interface AplRelator {
	
	public void salvar(Relator relator,Afastamento afastamento);
	
	public Relator buscaPorAfastamento(String id_afastamento);

}