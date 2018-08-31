package br.ufes.scap.secretaria.visao;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.visao.PermissaoPage;
import br.ufes.scap.nucleo.visao.TemplatePage;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;
import br.ufes.scap.secretaria.controle.PessoaController;

public class PessoaBusca extends TemplatePage {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaController pessoaControle;
	
	@Inject
    private AplPessoa aplPessoa;
	
	@Inject
	private Usuario usuarioWeb;
	
	public PessoaBusca() {
		
		Pessoa pessoa_aux = new Pessoa();
        PageParameters params = new PageParameters();
        pessoa_aux = aplPessoa.buscaMatricula(usuarioWeb.getMatricula());
    	if(!(pessoa_aux.getTipoPessoa().equals("2"))) {
    		String info = "Somente secretários do departamento tem acesso a esssa tarefa";
    		params.add("mensagem",info);
			setResponsePage(PermissaoPage.class,params);
			return;
    	}
		
		Pessoa pessoa = new Pessoa();
		Form<Object> form = new Form<Object>("form");
		TextField<String> text = new TextField<String>("nome", new PropertyModel<String>(pessoa,"nome"));
		TextField<String> text2 = new TextField<String>("sobrenome", new PropertyModel<String>(pessoa,"sobreNome"));
		Button button = new Button("button") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				pessoaControle.busca(text.getValue(), text2.getValue());
				setResponsePage(PessoaLista.class);

			}
		};
		
		add(form);
		form.add(text);
		form.add(text2);
		form.add(button);
		
	}

}