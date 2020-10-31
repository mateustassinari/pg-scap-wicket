package br.ufes.scap.nucleo.controle;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.wicket.Session;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.wicketstuff.javaee.injection.JavaEEComponentInjector;

import br.ufes.scap.nucleo.visao.ConfiguracaoPage;

public class WicketApplication extends WebApplication {
    
	@Override
    public Class<? extends WebPage> getHomePage() {
		return ConfiguracaoPage.class;
	}
    
    public Session newSession(Request request, Response response) {
    	return new UserSession(request);
    }
    
    @Override
    public void init() {
            
    		super.init();
            getComponentInstantiationListeners().add(new JavaEEComponentInjector(this));
            // Get BeanManager.  
            BeanManager bm;  
            try {  
                bm = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");  
            } catch (NamingException e) {  
                throw new IllegalStateException("Unable to obtain CDI BeanManager", e);  
            }  
          
            // Configure CDI, disabling Conversations as we aren't using them
            new CdiConfiguration(bm).configure(this);  
            // add your configuration here  
            
    }
	
}