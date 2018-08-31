package br.ufes.scap.secretaria.aplicacao;

import br.ufes.scap.nucleo.dominio.TipoParentesco;

public interface AplParentesco {
	
	void salvar(String matricula1,String matricula2,TipoParentesco tipo);
	
	boolean checaParentesco(String matricula1,String matricula2);
	
}