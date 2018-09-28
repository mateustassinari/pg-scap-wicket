package br.ufes.scap.nucleo.controle;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.aplicacao.AplRelator;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.Relator;
import br.ufes.scap.secretaria.aplicacao.AplMandato;
import br.ufes.scap.secretaria.aplicacao.AplParentesco;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

@Stateless
public class RelatorController {

	private String idAfastamento;
	
	private String notificacao = "";

	@Inject
	private AplRelator aplRelator;

	@Inject
	private AplAfastamento aplAfastamento;

	@Inject
	private AplPessoa aplPessoa;

	@Inject
	private AplParentesco aplParentesco;
	
	@Inject
    private AplMandato aplMandato;

	public boolean salva(String matricula) {
		
		Relator relator = new Relator();
		Afastamento afastamento = aplAfastamento.buscaId(idAfastamento);
		Pessoa pessoaaux = new Pessoa();
		pessoaaux = aplPessoa.buscaMatricula(matricula);
		
		if (pessoaaux == null || pessoaaux.getTipoPessoa().equals("2")) {
			notificacao = "Matrícula não existe ou o Relator não pode ser um secretário";
			return false;
		}
		
		if (aplParentesco.checaParentesco(afastamento.getSolicitante().getMatricula(),
				pessoaaux.getMatricula())) {
			notificacao = "Relator não pode ser um parente do solicitante do afastamento";
			return false;
		} 
		
		if(pessoaaux.getId_pessoa().toString().equals(afastamento.getSolicitante().getId_pessoa().toString())) {
			notificacao = "Relator não pode ser o solicitante do afastamento";
			return false;
		} else {
			relator.setRelator(pessoaaux);
			aplRelator.salvar(relator, afastamento);
			return true;
			}
	}
	
	public boolean verifica(Pessoa pessoa_aux, Afastamento afastamento) {
		
		if(!(aplMandato.checaMandato(pessoa_aux.getId_pessoa().toString()))) {
			notificacao = "Somente o chefe do departamento tem acesso a essa tarefa";
  		  	return false;
		}
  	
		if(!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("INICIADO")) {
			notificacao = "O afastamento não se encontra no status: INICIADO";
			return false;
		}
		return true;
	}

	public String getIdAfastamento() {
		return idAfastamento;
	}

	public void setIdAfastamento(String idAfastamento) {
		this.idAfastamento = idAfastamento;
	}

	public String getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(String notificacao) {
		this.notificacao = notificacao;
	}

}
