package br.ufes.scap.secretaria.persistencia;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.persistencia.BaseDAO;

public interface PessoaDAO extends BaseDAO<Pessoa> {
	
	public Pessoa buscaMatricula(String Matricula);
	
	public Pessoa buscaId(Long id_pessoa);
	
	public List<Pessoa> buscaNome(String nome,String sobreNome);
	
	public List<Pessoa> listaProfessores();

}