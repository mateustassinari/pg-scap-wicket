package br.ufes.scap.nucleo.dominio;

public enum TipoParecer {
	
	FAVORAVEL("FAVORAVEL"),   
    DESFAVORAVEL("DESFAVORAVEL");
	
	private String julgamento;  
	
	TipoParecer(String tipo_parecer){  
	        this.julgamento = tipo_parecer;  
	}  
	
	public void setJulgamento(String julgamento){
		this.julgamento = julgamento;
	}
	
	public String get(){  
	        return julgamento;  
	}
}