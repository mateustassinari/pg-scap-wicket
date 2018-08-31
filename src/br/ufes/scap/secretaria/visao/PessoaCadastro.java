package br.ufes.scap.secretaria.visao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.visao.AfastamentoBuscar;
import br.ufes.scap.nucleo.visao.PermissaoPage;
import br.ufes.scap.nucleo.visao.TemplatePage;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;
import br.ufes.scap.secretaria.controle.PessoaController;

public class PessoaCadastro extends TemplatePage {

	private static final long serialVersionUID = 1L;

	private List<String> tipoPessoaChoices = new ArrayList<String>();
	
	@Inject
	private PessoaController pessoaControle;
	
	@Inject
	private AplPessoa aplPessoa;
	
	@Inject
	private Usuario usuarioWeb;
	
	public PessoaCadastro(){
	
		Pessoa pessoa_aux = new Pessoa();
        PageParameters params = new PageParameters();
        pessoa_aux = aplPessoa.buscaMatricula(usuarioWeb.getMatricula());
      	if(!(pessoa_aux.getTipoPessoa().equals("2"))) {
      		String info = "Somente secretários do departamento tem acesso a esssa tarefa";
            params.add("mensagem",info);
			setResponsePage(PermissaoPage.class,params);
			return;
      	}

		tipoPessoaChoices.add("Professor");
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
		        setResponsePage(AfastamentoBuscar.class);
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