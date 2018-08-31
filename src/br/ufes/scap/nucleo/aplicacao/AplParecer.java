package br.ufes.scap.nucleo.aplicacao;

import java.util.List;

import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Parecer;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.TipoParecer;

public interface AplParecer {
	
	public void salvar(Parecer parecer,Afastamento afastamento,Pessoa usuario,TipoParecer tipoParecer);
	
	public List<Parecer> buscaPorAfastamento(String id_afastamento);
	
}