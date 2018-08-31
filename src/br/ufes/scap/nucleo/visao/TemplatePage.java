package br.ufes.scap.nucleo.visao;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;

public class TemplatePage extends WebPage {
    
	private static final long serialVersionUID = 5218374612129395403L;
    
	public static final String CONTENT_ID = "contentComponent";
    
	private Component headerPanel;
    
	public TemplatePage(){
    
		add(headerPanel = new HeaderPanel("headerPanel"));
    
	}
    
    public Component getHeaderPanel() {
    
    	return headerPanel;
    
    }
    
}