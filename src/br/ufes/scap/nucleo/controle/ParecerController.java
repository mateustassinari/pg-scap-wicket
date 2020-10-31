package br.ufes.scap.nucleo.controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.aplicacao.AplParecer;
import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Parecer;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.Relator;
import br.ufes.scap.nucleo.dominio.TipoParecer;

@Stateless
public class ParecerController {

	private String idAfastamento;

	private String notificacao = "";
	
	@Inject
	private Usuario usuarioWeb;
	
	@Inject
	private AplParecer aplParecer;
	
	@Inject
	private AplAfastamento aplAfastamento;
	
	public List<ParecerLista> listar(){
		
		List<Parecer> listaDAO = aplParecer.buscaPorAfastamento(idAfastamento);
		List<ParecerLista> tabela = new ArrayList<ParecerLista>();
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yy");
		for(Integer i=0;i<listaDAO.size();i++){
			ParecerLista elemento = new ParecerLista();
			elemento.setNomeCriador(listaDAO.get(i).getRelator().getNome()+" "+listaDAO.get(i).getRelator().getSobreNome());
			elemento.setData(formatada.format(listaDAO.get(i).getData_parecer().getTime()));
			elemento.setJulgamento(listaDAO.get(i).getJulgamento().get());
			elemento.setMotivo(listaDAO.get(i).getMotivoIndeferimento());
			tabela.add(elemento);
		}
		return tabela;
	}
	
	public boolean salvar(String motivo, TipoParecer tipoParecer){
		
		Afastamento afastamento = new Afastamento();
		afastamento = aplAfastamento.buscaId(idAfastamento);
		Parecer parecer = new Parecer();
					
		Calendar cal = Calendar.getInstance();
		
		parecer.setMotivoIndeferimento(motivo);
		parecer.setJulgamento(tipoParecer);
		parecer.setRelator(usuarioWeb.getLogado());
		parecer.setAfastamento(afastamento);
		parecer.setData_parecer(cal);
		
		aplParecer.salvar(parecer,afastamento,usuarioWeb.getLogado(),tipoParecer);
		return true;
	}
	
	public boolean verifica(Pessoa pessoa_aux, Afastamento afastamento, Relator relator) {
		
		if(!(pessoa_aux.getTipoPessoa().equals("1"))) {
        	if(afastamento.getTipoAfastamento().getTipoAfastamento().equals("INTERNACIONAL")) {  
        		if((!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("APROVADO_DI")) && (!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("APROVADO_CT"))){
        			notificacao = "O afastamento não se encontra no status: APROVADO_DI ou APROVADO_CT";
        			return false;
        		}
        	} else {
        		notificacao = "Você não pode deferir um parecer para esse tipo de afastamento";
    			return false;
        		}
     	}

    	if(afastamento.getSolicitante().getMatricula().equals(pessoa_aux.getMatricula())) {
    		notificacao = "Você não pode deferir um parecer para o seu próprio afastamento";
    		return false;
    	}
    	
    	if(afastamento.getTipoAfastamento().getTipoAfastamento().equals("INTERNACIONAL")) {
			if(!pessoa_aux.getTipoPessoa().equals("2")) {
				if(!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("LIBERADO")){
					notificacao = "O afastamento não se encontra no status: LIBERADO";
					return false;
				}
				if((!(relator.getRelator().getMatricula().equals(pessoa_aux.getMatricula())))) {
					notificacao = "Somente o relator escolhido pode deferir um parecer para esse afastamento";
					return false;
				}
			}		
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
