package br.ufes.scap.nucleo.visao;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import br.ufes.scap.nucleo.controle.UserSession;
import br.ufes.scap.secretaria.visao.MandatoCadastro;
import br.ufes.scap.secretaria.visao.PessoaBusca;
import br.ufes.scap.secretaria.visao.PessoaCadastro;

public class HeaderPanel extends Panel {
 
	private static final long serialVersionUID = 5218374612129395403L;
 
	public HeaderPanel(String id) {

    	super(id);
        add(new BookmarkablePageLink<Void>("afastamentoBusca",AfastamentoBuscar.class));   
        add(new BookmarkablePageLink<Void>("afastamentoFormulario",AfastamentoCadastro.class)); 
        add(new BookmarkablePageLink<Void>("pessoaBusca",PessoaBusca.class));
        add(new BookmarkablePageLink<Void>("pessoaFormulario",PessoaCadastro.class));
        add(new BookmarkablePageLink<Void>("MandatoFormulario",MandatoCadastro.class));          
        add(new Link<Void>("logout") {
            
			private static final long serialVersionUID = 1L;
			
			@Override
            public void onClick() {
            	UserSession.getInstance().invalidate();
            	setResponsePage(LoginPage.class);
            }

			@Override
			public MarkupContainer setDefaultModel(IModel<?> model) {
				return null;
			}
        
        });

    }
}