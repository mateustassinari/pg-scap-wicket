package br.ufes.scap.nucleo.controle;

import javax.ejb.Stateless;

import br.ufes.scap.nucleo.dominio.Pessoa;

@Stateless
public class VerificaPermissaoController {

	private String notificacao = "";
	
	public boolean verifica_secre(Pessoa pessoa_aux) {
		
		if(!(pessoa_aux.getTipoPessoa().equals("2"))) {
      		notificacao = "Somente secretários do departamento tem acesso a esssa tarefa";
      		return false;
		}
		return true;
	}
	
	public boolean verifica_prof(Pessoa pessoa_aux) {
		
		if(!(pessoa_aux.getTipoPessoa().equals("1"))) {
			notificacao = "Somente professores tem acesso a essa tarefa";
			return false;
		}
		return true;
	}

	public String getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(String notificacao) {
		this.notificacao = notificacao;
	}
	
}
