package br.ufes.scap.nucleo.persistencia;

public interface BaseDAO<T> {
	
	public void salvar (T object);
	
	public T merge(T object);
	
	public void delete(T object);

}