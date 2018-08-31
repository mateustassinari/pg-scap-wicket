package br.ufes.scap.nucleo.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import br.ufes.scap.nucleo.dominio.Afastamento;

@Stateless
public class JPAAfastamentoDAO extends JPABaseDAO<Afastamento> implements AfastamentoDAO {
		
	@PersistenceContext
	private EntityManager manager;
	
	protected EntityManager getEntityManager() {
		return manager;
	}
	
	@Override
	public Afastamento buscaId(String id_afastamento) {
		Afastamento afastamento = new Afastamento();
		try{
			Query q = manager.createQuery("SELECT a FROM Afastamento a WHERE a.id_afastamento = " + id_afastamento);
			afastamento = (Afastamento)q.getSingleResult();
			return afastamento;
		}catch(NoResultException e1) {
			System.out.println("Afastamento inexistente");
			return null;
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
			return null;
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Afastamento> listaAfastamentos() {
		List<Afastamento> lista = new ArrayList<Afastamento>();		
		try{
			Query q = manager.createQuery("SELECT a FROM Afastamento a");
			lista = q.getResultList();
			return lista;
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
			return null;
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}
	
}