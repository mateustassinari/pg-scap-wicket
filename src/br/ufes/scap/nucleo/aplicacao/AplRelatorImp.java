package br.ufes.scap.nucleo.aplicacao;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Relator;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.persistencia.AfastamentoDAO;
import br.ufes.scap.nucleo.persistencia.RelatorDAO;

@Stateless
public class AplRelatorImp implements AplRelator{											
	
	@Inject
	private AfastamentoDAO afastamentoDAO;
	
	@Inject
	private RelatorDAO relatorDAO;
		
	@Override
	public void salvar(Relator relator, Afastamento afastamento) {
		
		relator.setAfastamento(afastamento);
		
		SituacaoSolic situacao = SituacaoSolic.LIBERADO;
		afastamento.setSituacaoSolicitacao(situacao);
		
		afastamentoDAO.merge(afastamento);
		
		
		relatorDAO.salvar(relator);
	}

	@Override
	public Relator buscaPorAfastamento(String id_afastamento) {
		return relatorDAO.buscaPorAfastamento(id_afastamento);
	}

}