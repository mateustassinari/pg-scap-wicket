package br.ufes.scap.nucleo.aplicacao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Parecer;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.dominio.TipoParecer;
import br.ufes.scap.nucleo.persistencia.AfastamentoDAO;
import br.ufes.scap.nucleo.persistencia.ParecerDAO;
import br.ufes.scap.secretaria.persistencia.MandatoDAO;

@Stateless
public class AplParecerImp implements AplParecer{

	@Inject
	private ParecerDAO parecerDAO;
	
	@Inject
	private AfastamentoDAO afastamentoDAO;
	
	@Inject
	private MandatoDAO mandatoDAO;
	
	@Override
	public void salvar(Parecer parecer, Afastamento afastamento, Pessoa usuario, TipoParecer tipoParecer) {
		parecerDAO.salvar(parecer);
		if(afastamento.getTipoAfastamento().getTipoAfastamento().equals("INTERNACIONAL")){
			if(usuario.getTipoPessoa().equals("2")) {
				if(afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("APROVADO_DI")){
					if(tipoParecer.get().equals("FAVORAVEL")) {
						SituacaoSolic situacao = SituacaoSolic.APROVADO_CT;
						afastamento.setSituacaoSolicitacao(situacao);
					}else{
						SituacaoSolic situacao = SituacaoSolic.REPROVADO;
						afastamento.setSituacaoSolicitacao(situacao);
						}
				} else {
						if(tipoParecer.get().equals("FAVORAVEL")) {
							SituacaoSolic situacao = SituacaoSolic.APROVADO_PRPPG;
							afastamento.setSituacaoSolicitacao(situacao);
						}else{
							SituacaoSolic situacao = SituacaoSolic.REPROVADO;
							afastamento.setSituacaoSolicitacao(situacao);
							}
						}	
			afastamentoDAO.merge(afastamento);
			} else if(!(mandatoDAO.checaMandato(usuario.getId().toString()))) {
					if(tipoParecer.get().equals("FAVORAVEL")){
			       		SituacaoSolic situacao = SituacaoSolic.APROVADO_DI;
			       		afastamento.setSituacaoSolicitacao(situacao);
			       	}else{
			       		SituacaoSolic situacao = SituacaoSolic.REPROVADO;
			       		afastamento.setSituacaoSolicitacao(situacao);
			       		}
			       	afastamentoDAO.merge(afastamento);
					} else if(tipoParecer.get().equals("DESFAVORAVEL")){
							SituacaoSolic situacao = SituacaoSolic.REPROVADO;
							afastamento.setSituacaoSolicitacao(situacao);
							} 
							else {
							SituacaoSolic situacao = SituacaoSolic.INICIADO;
							afastamento.setSituacaoSolicitacao(situacao);
							}
					afastamentoDAO.merge(afastamento);
		}
		
	}		
		
	@Override
	public List<Parecer> buscaPorAfastamento(String id_afastamento) {
		return parecerDAO.buscaPorAfastamento(id_afastamento);
	}

}