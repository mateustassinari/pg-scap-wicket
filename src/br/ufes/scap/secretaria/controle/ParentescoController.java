package br.ufes.scap.secretaria.controle;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.TipoParentesco;
import br.ufes.scap.secretaria.aplicacao.AplParentesco;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

@Stateless
public class ParentescoController {

	private String matricula1;
	
	private String notificacao = "";

	@Inject
	private AplPessoa aplPessoa;
	
	@Inject
	private AplParentesco aplParentesco;
	
	public boolean salva(String matricula2,TipoParentesco tipo) {
		
		Pessoa pessoaaux = new Pessoa();
		pessoaaux = aplPessoa.buscaMatricula(matricula2);
		if(pessoaaux == null){
			notificacao = "Matrícula não existe";
			return false;
		}
		
		if(pessoaaux.getTipoPessoa().equals("1")) {
			if(aplParentesco.checaParentesco(matricula1,matricula2)) {
				notificacao = "Parentesco já foi cadastrado";
				return false;
			}
			aplParentesco.salvar(matricula1,matricula2,tipo);
			return true;
		} else {
			notificacao =  "É possivel cadastrar parentesco somente entre professores";
			return false;
			}
	}
	
	public String getMatricula1() {
		return matricula1;
	}

	public void setMatricula1(String matricula1) {
		this.matricula1 = matricula1;
	}

	public String getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(String notificacao) {
		this.notificacao = notificacao;
	}
	
}