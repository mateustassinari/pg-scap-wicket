package br.ufes.scap.nucleo.persistencia;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Parecer;

public interface ParecerDAO extends BaseDAO<Parecer> {
	
	public Parecer buscaId(String id_parecer);
	
	public List<Parecer> buscaPorAfastamento(String id_afastamento);

}