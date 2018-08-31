package br.ufes.scap.secretaria.aplicacao;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Pessoa;

public interface AplPessoa {
	
	void salvar(Pessoa novoUsuario);
	
	Pessoa buscaMatricula(String matricula);
	
	List<Pessoa> buscaNome(String nome,String sobreNome);

}