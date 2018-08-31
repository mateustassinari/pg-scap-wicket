package br.ufes.scap.secretaria.persistencia;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Documento;
import br.ufes.scap.nucleo.persistencia.BaseDAO;

public interface DocumentoDAO extends BaseDAO<Documento> {
	
	public Documento buscaId(String id_documento);
	
	public List<Documento> buscaPorAfastamento(String id_afastamento);
	
}