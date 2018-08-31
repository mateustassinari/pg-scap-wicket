package br.ufes.scap.nucleo.aplicacao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Onus;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.SituacaoSolic;
import br.ufes.scap.nucleo.dominio.TipoAfastamento;
import br.ufes.scap.nucleo.persistencia.AfastamentoDAO;

@Stateless
public class AplAfastamentoImp implements AplAfastamento{

	@Inject
	private AfastamentoDAO afastamentoDAO;
	
	@Override
	public void salvar(Afastamento novoAfastamento,Pessoa solicitante,TipoAfastamento tipo, Onus onusAfastamento, String motivo_afast) {
		novoAfastamento.setSolicitante(solicitante);
		novoAfastamento.setTipoAfastamento(tipo);
		novoAfastamento.setOnus(onusAfastamento);
		if(!(motivo_afast == null)) {
			novoAfastamento.setMotivo_afast(motivo_afast);
		}
		if(tipo.getTipoAfastamento().equals("NACIONAL")){
			SituacaoSolic situacao = SituacaoSolic.LIBERADO;
			novoAfastamento.setSituacaoSolicitacao(situacao);
		}else{
			SituacaoSolic situacao = SituacaoSolic.INICIADO;
			novoAfastamento.setSituacaoSolicitacao(situacao);
		}
		afastamentoDAO.salvar(novoAfastamento);
				
	}

	@Override
	public List<Afastamento> listaAfastamentos() {
		return afastamentoDAO.listaAfastamentos();
	}

	@Override
	public Afastamento buscaId(String id_afastamento) {
		return afastamentoDAO.buscaId(id_afastamento);
	}

	@Override
	public void mudarStatus(Afastamento afastamento, String novoStatus,Pessoa logado) {
		SituacaoSolic situacao = SituacaoSolic.valueOf(novoStatus);
		afastamento.setSituacaoSolicitacao(situacao);
		afastamentoDAO.merge(afastamento);
	}

}