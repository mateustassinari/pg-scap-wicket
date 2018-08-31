package br.ufes.scap.nucleo.visao;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PermissaoPage extends TemplatePage {
    
	private static final long serialVersionUID = -7465108755276912649L;
    	
    public PermissaoPage(PageParameters p) {
 
    	FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
		if(!(p.isEmpty())) {
        	error(p.get("mensagem"));
        }
    
    }
               
}