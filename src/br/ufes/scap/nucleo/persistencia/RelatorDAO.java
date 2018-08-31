package br.ufes.scap.nucleo.persistencia;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Relator;

public interface RelatorDAO extends BaseDAO<Relator> {
	
	public Relator buscaId(String id_relator);
	
	public Relator buscaPorAfastamento(String id_afastamento);
	
	public List<Relator> listaRelatores();
}