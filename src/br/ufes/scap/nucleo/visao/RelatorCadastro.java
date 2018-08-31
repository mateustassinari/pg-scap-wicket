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
import br.ufes.scap.secretaria.aplicacao.AplMandato;
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
	
	@Inject
    private AplMandato aplMandato;
	
	public RelatorCadastro(PageParameters p) {
		
		Pessoa pessoa_aux = new Pessoa();
		Afastamento afastamento = new Afastamento();
        PageParameters params = new PageParameters();
        pessoa_aux = aplPessoa.buscaMatricula(usuarioWeb.getMatricula());
    	afastamento = aplAfastamento.buscaId(relatorControle.getIdAfastamento());
    	if(!(aplMandato.checaMandato(pessoa_aux.getId_pessoa().toString()))) {
    		  String info = "Somente o chefe do departamento tem acesso a essa tarefa";
    		  params.add("mensagem",info);
			  setResponsePage(AfastamentoMostrar.class,params);
			  return;
    	}
    	
    	if(!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("INICIADO")) {
    		String info = "O afastamento não se encontra no status: INICIADO";
    		params.add("mensagem",info);
			setResponsePage(AfastamentoMostrar.class,params);
			return;
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