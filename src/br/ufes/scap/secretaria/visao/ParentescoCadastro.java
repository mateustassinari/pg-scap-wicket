package br.ufes.scap.secretaria.visao;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.dominio.Parentesco;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.TipoParentesco;
import br.ufes.scap.nucleo.visao.AfastamentoBuscar;
import br.ufes.scap.nucleo.visao.TemplatePage;
import br.ufes.scap.secretaria.controle.ParentescoController;

public class ParentescoCadastro extends TemplatePage {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ParentescoController parentescoControle;
	
	public ParentescoCadastro(PageParameters p) {
		
		Parentesco novoParentesco = new Parentesco();
        PageParameters params = new PageParameters();
		Pessoa pessoa = new Pessoa();
		Form<Object> form = new Form<Object>("form");
		TextField<String> text = new TextField<String>("matriculaParente", new PropertyModel<String>(pessoa,"matricula"));
		DropDownChoice<TipoParentesco> text3 = new DropDownChoice<TipoParentesco>("tipoParentesco",new PropertyModel<TipoParentesco>(novoParentesco,"tipoParentesco"),Arrays.asList(TipoParentesco.values()));

		Button button = new Button("button") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
			
				if(parentescoControle.salva(pessoa.getMatricula(),novoParentesco.getTipoParentesco())) {
					setResponsePage(AfastamentoBuscar.class);
				} else {
					params.add("mensagem",parentescoControle.getNotificacao());
					setResponsePage(ParentescoCadastro.class,params);
					}
			}
			
		};

		add(form);
		form.add(text);
		form.add(text3);
		form.add(button);

		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
		if(!(p.isEmpty())) {
        	error(p.get("mensagem"));
        }		
		
	}
	
}