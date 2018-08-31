package br.ufes.scap.nucleo.dominio;

public enum TipoAfastamento {
	
	NACIONAL("NACIONAL"),   
    INTERNACIONAL("INTERNACIONAL");
	
	private String tipoAfastamento;  
	
	TipoAfastamento(String tipo_Afastamento){  
	       this.tipoAfastamento = tipo_Afastamento;  
	}
	
	public void setTipoAfastamento(String tipo){
		this.tipoAfastamento = tipo;
	}
	
	public String getTipoAfastamento(){  
	       return tipoAfastamento;  
	}
}