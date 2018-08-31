package br.ufes.scap.secretaria.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import br.ufes.scap.nucleo.dominio.Parentesco;
import br.ufes.scap.nucleo.persistencia.JPABaseDAO;

@Stateless
public class JPAParentescoDAO extends JPABaseDAO<Parentesco> implements ParentescoDAO{

	@PersistenceContext
	private EntityManager manager;

	protected EntityManager getEntityManager() {
		return manager;
	}
	
	@Override
	public Parentesco buscaId(String id_parentesco) {
		Parentesco parentesco = new Parentesco();
		try{
			Query q = manager.createQuery("SELECT a FROM Parentesco a WHERE a.id_parentesco = " + id_parentesco);
			parentesco = (Parentesco)q.getSingleResult();
			return parentesco;
		}catch(NoResultException e1) {
			System.out.println("Pessoa inexistente");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean checaParentesco(String id_pessoa1, String id_pessoa2) {
		@SuppressWarnings("unused")
		Parentesco parentesco = new Parentesco();
		try{
			Query q = manager.createQuery("SELECT a FROM Parentesco a WHERE a.pessoa1 = '" + id_pessoa1 + "' AND a.pessoa2 = '" + id_pessoa2+"'");
			parentesco = (Parentesco)q.getSingleResult();
			return true;
		}catch(NoResultException e1) {
			System.out.println("Pessoa inexistente");
			return false;
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return false;
	}
		
}