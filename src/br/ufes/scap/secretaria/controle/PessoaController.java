package br.ufes.scap.secretaria.controle;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

@Stateless
public class PessoaController {
	
	private List<Pessoa> listaPessoa;
	
	@Inject
	private AplPessoa aplPessoa;
	
	public void salvar(Pessoa pessoa){
		aplPessoa.salvar(pessoa);
	}
	
	public void busca(String nome,String sobreNome){
		this.listaPessoa = aplPessoa.buscaNome(nome, sobreNome);
	}

	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}

}