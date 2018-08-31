package br.ufes.scap.secretaria.persistencia;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Mandato;
import br.ufes.scap.nucleo.persistencia.BaseDAO;

public interface MandatoDAO extends BaseDAO<Mandato> {
	
	public Mandato buscaId(String id_mandato);
	
	public boolean checaMandato(String id_pessoa);
	
	public List<Mandato> listaMandatos();

}