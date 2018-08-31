package br.ufes.scap.nucleo.persistencia;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.persistencia.BaseDAO;

public interface AfastamentoDAO extends BaseDAO<Afastamento> {

	public Afastamento buscaId(String id_afastamento);
	
	public List<Afastamento> listaAfastamentos();

}