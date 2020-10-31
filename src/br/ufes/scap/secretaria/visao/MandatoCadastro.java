package br.ufes.scap.secretaria.visao;

import java.text.ParseException;
import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.extensions.yui.calendar.DatePicker;

import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.controle.VerificaPermissaoController;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.visao.AfastamentoBuscar;
import br.ufes.scap.nucleo.visao.PermissaoPage;
import br.ufes.scap.nucleo.visao.TemplatePage;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;
import br.ufes.scap.secretaria.controle.MandatoController;

public class MandatoCadastro extends TemplatePage {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MandatoController mandatoControle;

	@Inject
	private VerificaPermissaoController verificaControle;
	
	@Inject
    private AplPessoa aplPessoa;

	@Inject
	private Usuario usuarioWeb;
	
	private final Date date = new Date();
	private final Date date2 = new Date();
	
	public MandatoCadastro(PageParameters p) {
	
		Pessoa pessoa_aux = new Pessoa();
        PageParameters params = new PageParameters();
        pessoa_aux = aplPessoa.buscaMatricula(usuarioWeb.getMatricula());
    
        if(!verificaControle.verifica_secre(pessoa_aux)) {
        	params.add("mensagem",verificaControle.getNotificacao());
        	setResponsePage(PermissaoPage.class,params);
        }
     	
		Pessoa pessoa = new Pessoa();
		Form<Object> form = new Form<Object>("form");
		TextField<String> text3 = new TextField<String>("matriculaoutra", new PropertyModel<String>(pessoa,"matricula"));
		DateTextField text4 = new DateTextField("datainicio",new PropertyModel<Date>(this, "date"));
		DateTextField text5 = new DateTextField("datafim",new PropertyModel<Date>(this, "date2"));
		DatePicker datePicker = new DatePicker() {
	            
			private static final long serialVersionUID = 1L;

			@Override
	        protected String getAdditionalJavaScript()
	        {
				return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
	        }
		};
	    datePicker.setShowOnFieldClick(true);
	    datePicker.setAutoHide(true);
	    DatePicker datePicker2 = new DatePicker() {
	            
	    	private static final long serialVersionUID = 1L;

			@Override
	        protected String getAdditionalJavaScript()
	        {
				return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
	        }
	    };
	    datePicker2.setShowOnFieldClick(true);
	    datePicker2.setAutoHide(true);
	    text4.add(datePicker);
	    text5.add(datePicker2);
		
		Button buttonmandato = new Button("buttonMandato") {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				
				try {
					if(mandatoControle.salva(pessoa.getMatricula(),text4.getValue(),text5.getValue())) {
						setResponsePage(AfastamentoBuscar.class);				
					} else {
						params.add("mensagem",mandatoControle.getNotificacao());
						setResponsePage(MandatoCadastro.class,params);
						}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}
	
		};

		add(form);
		form.add(text3);
		form.add(text4);
		form.add(text5);
		form.add(buttonmandato);
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
		if(!(p.isEmpty())) {
        	error(p.get("mensagem"));
        }
		
	}
	
}