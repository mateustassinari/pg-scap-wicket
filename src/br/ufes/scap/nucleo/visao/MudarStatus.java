package br.ufes.scap.nucleo.visao;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.controle.AfastamentoController;

public class MudarStatus extends TemplatePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private AfastamentoController afastControle; 
	
	@Inject
	private Usuario usuarioWeb;

	private List<String> casoProfessor = Arrays.asList("CANCELADO");
	
	private List<String> casoSecretario = Arrays.asList("APROVADO_DI","ARQUIVADO");
	
	private DropDownChoice<String> prof;
	
	private DropDownChoice<String> secre;
	
	public MudarStatus(PageParameters p) {
		
        PageParameters params = new PageParameters();
		Form<Object> form = new Form<Object>("form");
		add(form);
		if(usuarioWeb.getLogado().getTipoPessoa().equals("1")) {
			prof = new DropDownChoice<String>("status",new Model<>(),casoProfessor);
	 		form.add(prof);
		} else {
			secre = new DropDownChoice<String>("status",new Model<>(),casoSecretario);
	 		form.add(secre);
			}
		
		Button button = new Button("button") {
				
				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit() {
		
					if(usuarioWeb.getLogado().getTipoPessoa().equals("1")) {
						if(afastControle.mudarStatus(prof.getDefaultModelObjectAsString())) {
							setResponsePage(AfastamentoBuscar.class);
						} else {
							params.add("mensagem",afastControle.getNotificacao());
							setResponsePage(MudarStatus.class,params);
							}
					} else {
						if(afastControle.mudarStatus(secre.getDefaultModelObjectAsString())) {
							setResponsePage(AfastamentoBuscar.class);
						} else {
							params.add("mensagem",afastControle.getNotificacao());
							setResponsePage(MudarStatus.class,params);
							}
						}
				}
				
		 };

		form.add(button);
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
		if(!(p.isEmpty())) {
        	error(p.get("mensagem"));
        }

	}
	
}