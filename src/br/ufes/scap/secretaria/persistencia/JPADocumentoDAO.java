package br.ufes.scap.secretaria.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import br.ufes.scap.nucleo.dominio.Documento;
import br.ufes.scap.nucleo.persistencia.JPABaseDAO;

@Stateless
public class JPADocumentoDAO extends JPABaseDAO<Documento> implements DocumentoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	protected EntityManager getEntityManager() {
		return manager;
	}
	
	@Override
	public Documento buscaId(String id_documento) {
		Documento documento = new Documento();
		try{
			Query q = manager.createQuery("SELECT a FROM Documento a WHERE a.id_documento = " + id_documento);
			documento = (Documento)q.getSingleResult();
			return documento;
		}catch(NoResultException e1) {
			System.out.println("documento inexistente");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Documento> buscaPorAfastamento(String id_afastamento) {
		List<Documento> lista = new ArrayList<Documento>();
		try{
			Query q = manager.createQuery("SELECT a FROM Documento a WHERE a.afastamento = " + id_afastamento);
			lista = q.getResultList();
			return lista;
		}catch(NoResultException e1) {
			System.out.println("documento inexistente");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}
	
}