package br.ufes.scap.nucleo.visao;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.controle.RelatorController;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

public class RelatorCadastro extends TemplatePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private RelatorController relatorControle;
	
	@Inject
    private AplPessoa aplPessoa;
	
	@Inject
	private Usuario usuarioWeb;
	
	@Inject
    private AplAfastamento aplAfastamento;
	
	public RelatorCadastro(PageParameters p) {
		
		Pessoa pessoa_aux = new Pessoa();
		Afastamento afastamento = new Afastamento();
        PageParameters params = new PageParameters();
        pessoa_aux = aplPessoa.buscaMatricula(usuarioWeb.getMatricula());
    	afastamento = aplAfastamento.buscaId(relatorControle.getIdAfastamento());
    
    	if(!relatorControle.verifica(pessoa_aux, afastamento)) {
    		params.add("mensagem",relatorControle.getNotificacao());
        	setResponsePage(AfastamentoMostrar.class,params);
    	}
    	
    	Pessoa pessoa = new Pessoa();
		Form<Object> form = new Form<Object>("form");
		TextField<String> text = new TextField<String>("matriculaRelator", new PropertyModel<String>(pessoa,"matricula"));
		Button button = new Button("button") {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
					if(relatorControle.salva(pessoa.getMatricula())) {
						setResponsePage(AfastamentoBuscar.class);
					} else {
						params.add("mensagem",relatorControle.getNotificacao());
						setResponsePage(RelatorCadastro.class,params);
					   }
				}
			
		};

		add(form);
		form.add(text);
		form.add(button);	
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
		if(!(p.isEmpty())) {
        	error(p.get("mensagem"));
        }
		
	}
	
}