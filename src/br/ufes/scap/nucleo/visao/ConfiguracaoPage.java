package br.ufes.scap.nucleo.visao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;
import br.ufes.scap.secretaria.controle.PessoaController;

public class ConfiguracaoPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private List<String> tipoPessoaChoices = new ArrayList<String>();
	
	private List<Pessoa> lista = new ArrayList<Pessoa>();
		
	@Inject
	private PessoaController pessoaControle;
	
	@Inject
	private AplPessoa aplPessoa;
	
	public ConfiguracaoPage(){

		lista = aplPessoa.listaPessoas();
		if(lista.size() > 0) {
			setResponsePage(LoginPage.class);	
		}
		
		tipoPessoaChoices.add("Secretario");
		Pessoa pessoa = new Pessoa();
		Form<Object> form = new Form<Object>("form");
		TextField<String> text = new TextField<String>("nome", new PropertyModel<String>(pessoa,"nome"));
		TextField<String> text1 = new TextField<String>("sobrenome", new PropertyModel<String>(pessoa,"sobreNome"));
		TextField<String> text2 = new TextField<String>("email", new PropertyModel<String>(pessoa,"email"));
		TextField<String> text3 = new TextField<String>("matricula", new PropertyModel<String>(pessoa,"matricula"));
		TextField<String> text4 = new TextField<String>("telefone", new PropertyModel<String>(pessoa,"telefone"));
		PasswordTextField text5 = new PasswordTextField("password",new PropertyModel<String>(pessoa,"password"));
		DropDownChoice<String> text6 = new DropDownChoice<String>("tipoPessoa",new PropertyModel<String>(pessoa,"tipoPessoa"),tipoPessoaChoices);
		
		Button button = new Button("button") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				pessoaControle.salvar(pessoa);
		        setResponsePage(LoginPage.class);
			}
			
		};
		
		add(form);
		form.add(text);
		form.add(text1);
		form.add(text2);
		form.add(text3);
		form.add(text4);
		form.add(text5);
		form.add(text6);
		form.add(button);
	
	}
	
}