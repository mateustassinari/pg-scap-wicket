package br.ufes.scap.secretaria.aplicacao;

import java.util.List;


import br.ufes.scap.nucleo.dominio.Mandato;

public interface AplMandato {
	
	public void salvar(Mandato novoMandato,String matricula);
	
	public boolean checaMandato(String id_pessoa);
	
	public List<Mandato> busca();
		
}