package br.ufes.scap.nucleo.dominio;

public enum Onus {
	
	INEXISTENTE("INEXISTENTE"),   
    PARCIAL("PARCIAL"),
    TOTAL("TOTAL");
	
	private String onus;
	
	Onus(String tipo_Onus){
		this.onus= tipo_Onus;
	}
	
	public String getOnus() {
		return onus;
	}
}