package br.ufes.scap.nucleo.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import br.ufes.scap.nucleo.dominio.Parecer;

@Stateless
public class JPAParecerDAO extends JPABaseDAO<Parecer> implements ParecerDAO {

	@PersistenceContext
	private EntityManager manager;

	protected EntityManager getEntityManager() {
		return manager;
	}
	
	@Override
	public Parecer buscaId(String id_parecer) {
		Parecer parecer = new Parecer();
		try{
			Query q = manager.createQuery("SELECT a FROM Parecer a WHERE a.id_parecer = " + id_parecer);
			parecer = (Parecer)q.getSingleResult();
			return parecer;
		}catch(NoResultException e1) {
			System.out.println("parecer inexistente");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parecer> buscaPorAfastamento(String id_afastamento) {
		List<Parecer> lista = new ArrayList<Parecer>();		
		try{
			Query q = manager.createQuery("SELECT a FROM Parecer a WHERE a.afastamento = " + id_afastamento);
			lista = q.getResultList();
			return lista;
		}catch(NoResultException e1) {
			System.out.println("parecer inexistente");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}
		
}