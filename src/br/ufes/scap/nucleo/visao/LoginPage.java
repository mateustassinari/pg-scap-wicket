package br.ufes.scap.nucleo.visao;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

public class LoginPage extends WebPage {
   
    private static final long serialVersionUID = 5946349607750478191L;
    
    @Inject
	private Usuario usuarioWeb;
	
	@Inject
	private AplPessoa aplPessoa;
	
	public LoginPage(PageParameters p){
		
		Pessoa pessoa = new Pessoa();
		Form<Object> form = new Form<Object>("logar");
		TextField<String> text = new TextField<String>("matricula", new PropertyModel<String>(pessoa,"matricula"));
		PasswordTextField text1 = new PasswordTextField("password",new PropertyModel<String>(pessoa,"password"));

		Button button = new Button("buttonLogar") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				
				Pessoa pessoa2 = new Pessoa();
		        PageParameters params = new PageParameters();
				pessoa2 = aplPessoa.buscaMatricula(pessoa.getMatricula());
				if(pessoa2!=null){
					if(pessoa2.getPassword().equals(pessoa.getPassword())){
						usuarioWeb.login(pessoa2);
						UserSession.getInstance();
						setResponsePage(AfastamentoBuscar.class);
					}else {
						String info = "Senha Incorreta";
			            params.add("mensagem",info);
						setResponsePage(LoginPage.class,params);
					}
				}else{
					String info = "Matrícula Inexistente";
		            params.add("mensagem",info);
					setResponsePage(LoginPage.class,params);
				}
			}
			
		};
		
		add(form);
		form.add(text);
		form.add(text1);
		form.add(button);
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
		if(!(p.isEmpty())) {
        	error(p.get("mensagem"));
        }
		
	}
   
}