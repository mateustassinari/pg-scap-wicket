package br.ufes.scap.secretaria.aplicacao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.Professor;
import br.ufes.scap.nucleo.dominio.Secretario;
import br.ufes.scap.secretaria.persistencia.PessoaDAO;

@Stateless
public class AplPessoaImp implements AplPessoa{

	@Inject
	private PessoaDAO pessoaDAO;
	
	@Override
	public void salvar(Pessoa novoUsuario) {
	
		if(novoUsuario.getTipoPessoa().equals("Professor")){
			Professor novoProfessor = new Professor();
			novoProfessor.setNome(novoUsuario.getNome());
			novoProfessor.setSobreNome(novoUsuario.getSobreNome());
			novoProfessor.setEmail(novoUsuario.getEmail());
			novoProfessor.setTelefone(novoUsuario.getTelefone());
			novoProfessor.setMatricula(novoUsuario.getMatricula());
			novoProfessor.setTipoPessoa("1");
			novoProfessor.setPassword(novoUsuario.getPassword());
			pessoaDAO.salvar(novoProfessor);
		} else {
			Secretario novoSecretario = new Secretario();
			novoSecretario.setNome(novoUsuario.getNome());
			novoSecretario.setSobreNome(novoUsuario.getSobreNome());
			novoSecretario.setEmail(novoUsuario.getEmail());
			novoSecretario.setTelefone(novoUsuario.getTelefone());
			novoSecretario.setMatricula(novoUsuario.getMatricula());
			novoSecretario.setTipoPessoa("2");
			novoSecretario.setPassword(novoUsuario.getPassword());
			pessoaDAO.salvar(novoSecretario);
		}
	}

	@Override
	public List<Pessoa> buscaNome(String nome, String sobreNome) {
		List<Pessoa> lista = pessoaDAO.buscaNome(nome, sobreNome);		
		return lista;
	}

	@Override
	public Pessoa buscaMatricula(String matricula) {
		return pessoaDAO.buscaMatricula(matricula);
	}

}