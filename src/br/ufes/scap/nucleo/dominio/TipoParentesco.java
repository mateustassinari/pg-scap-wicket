package br.ufes.scap.nucleo.dominio;

public enum TipoParentesco {
	MATRIOMONIAL("MATRIOMONIAL"),   
    SANGUINEO("SANGUINEO");
	
	private String tipoParentesco;  
	
	TipoParentesco(String tipo_Parentesco){  
	       this.tipoParentesco = tipo_Parentesco;  
	}  
	  
	public String getTipoParentesco(){  
	       return tipoParentesco;  
	}
}