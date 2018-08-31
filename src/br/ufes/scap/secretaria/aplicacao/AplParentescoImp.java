package br.ufes.scap.secretaria.aplicacao;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Parentesco;
import br.ufes.scap.nucleo.dominio.TipoParentesco;
import br.ufes.scap.secretaria.persistencia.ParentescoDAO;
import br.ufes.scap.secretaria.persistencia.PessoaDAO;

@Stateless
public class AplParentescoImp implements AplParentesco{

	@Inject
	private PessoaDAO pessoaDAO;
	
	@Inject
	private ParentescoDAO parentescoDAO;
	
	@Override
	public void salvar(String matricula1, String matricula2, TipoParentesco tipo) {
		
		Parentesco parentesco = new Parentesco();
		parentesco.setPessoa1(pessoaDAO.buscaMatricula(matricula1));
		parentesco.setPessoa2(pessoaDAO.buscaMatricula(matricula2));
		parentesco.setTipoParentesco(tipo);
		parentescoDAO.salvar(parentesco);
		
		Parentesco parentesco2 = new Parentesco();
		parentesco2.setPessoa1(pessoaDAO.buscaMatricula(matricula2));
		parentesco2.setPessoa2(pessoaDAO.buscaMatricula(matricula1));
		parentesco2.setTipoParentesco(tipo);
		parentescoDAO.salvar(parentesco2);
		
	}

	@Override
	public boolean checaParentesco(String matricula1, String matricula2) {
		return parentescoDAO.checaParentesco(matricula1, matricula2);
	}

}