package br.ufes.scap.secretaria.persistencia;

import br.ufes.scap.nucleo.dominio.Parentesco;
import br.ufes.scap.nucleo.persistencia.BaseDAO;

public interface ParentescoDAO extends BaseDAO<Parentesco>{
	
	public Parentesco buscaId(String id_parentesco);
	
	public Boolean checaParentesco(String id_pessoa1, String id_pessoa2);

}