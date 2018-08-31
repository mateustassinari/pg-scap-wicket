package br.ufes.scap.nucleo.aplicacao;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import br.ufes.scap.nucleo.dominio.Pessoa;

@Stateful
@SessionScoped
public class Usuario {

	private Pessoa logado;
	
	public void login(Pessoa usuario) {
	    this.logado = usuario;
	}
	
	public boolean isLogado() {
	    return logado != null;
	}
	
	public String getMatricula(){
		return logado.getMatricula();
	}
	
	public Pessoa getLogado(){
		return logado;
	}
	
	public void logout(){
		this.logado = null;
	}
	
}