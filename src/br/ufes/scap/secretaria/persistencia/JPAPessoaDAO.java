package br.ufes.scap.secretaria.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.persistencia.JPABaseDAO;

@Stateless
public class JPAPessoaDAO extends JPABaseDAO<Pessoa> implements PessoaDAO{
	
	@PersistenceContext
	private EntityManager manager;
	
	protected EntityManager getEntityManager() {
		return manager;
	}
	
	@Override
	public Pessoa buscaMatricula(String matricula) {
		Pessoa pessoa = new Pessoa();
		try{
			Query q = manager.createQuery("SELECT p FROM Pessoa p WHERE p.matricula = " + matricula);
			pessoa = (Pessoa)q.getSingleResult();
			return pessoa;
		}catch(NoResultException e1) {
			System.out.println("Pessoa inexistente");
			return null;
		}catch(NonUniqueResultException e2){
			System.out.println("Mais de uma pessoa cadastada com a mesma matricula");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Pessoa buscaId(Long id_pessoa) {
		Pessoa pessoa = new Pessoa();
		try{
			Query q = manager.createQuery("SELECT a FROM Pessoa a WHERE a.id_pessoa = " + id_pessoa);
			pessoa = (Pessoa)q.getSingleResult();
			return pessoa;
		}catch(NoResultException e1) {
			System.out.println("Pessoa inexistente");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listaProfessores() {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		try{
			Query q = manager.createQuery("SELECT a FROM Pessoa a WHERE a.tipoPessoa = 1");
			lista = q.getResultList();
			return lista;
		}catch(NoResultException e1) {
			System.out.println("Pessoa inexistente");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listaPessoas() {
		List<Pessoa> lista = new ArrayList<Pessoa>();		
		try{
			Query q = manager.createQuery("SELECT a FROM Pessoa a");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> buscaNome(String nome, String sobreNome) {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		try{
			Query q = manager.createQuery("SELECT a FROM Pessoa a WHERE a.nome = '" + nome +"' AND a.sobreNome = '"+sobreNome+"'");
			lista = q.getResultList();
			return lista;
		}catch(NoResultException e1) {
			System.out.println("Pessoa inexistente");
		}catch(QueryTimeoutException e3){
			System.out.println("Query timmed out");
		}catch(Exception e4){
			e4.printStackTrace();
		}
		return null;
	}

}