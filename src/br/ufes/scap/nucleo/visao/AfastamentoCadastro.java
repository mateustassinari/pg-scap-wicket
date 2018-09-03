package br.ufes.scap.nucleo.visao;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.controle.AfastamentoController;
import br.ufes.scap.nucleo.controle.VerificaPermissaoController;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Onus;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.TipoAfastamento;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

public class AfastamentoCadastro extends TemplatePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private AfastamentoController afastControle;    
	
	@Inject
	private VerificaPermissaoController verificaControle;
	
    @Inject
	private Usuario usuarioWeb;

    @Inject
    private AplPessoa aplPessoa;
   
	@SuppressWarnings("unused")
	private final Date date = new Date();
	@SuppressWarnings("unused")
	private final Date date2 = new Date();
	@SuppressWarnings("unused")
	private final Date date3 = new Date();
	@SuppressWarnings("unused")
	private final Date date4 = new Date();

	public AfastamentoCadastro(PageParameters p) {
		
		Pessoa pessoa_aux = new Pessoa();
        PageParameters params = new PageParameters();
        pessoa_aux = aplPessoa.buscaMatricula(usuarioWeb.getMatricula());

        if(!verificaControle.verifica_prof(pessoa_aux)) {
        	params.add("mensagem",verificaControle.getNotificacao());
        	setResponsePage(PermissaoPage.class,params);
        }
        
		Afastamento novoAfastamento = new Afastamento();
		Form<Object> form = new Form<Object>("form");
		TextField<String> text = new TextField<String>("nomeevento", new PropertyModel<String>(novoAfastamento,"nome_evento"));
		TextField<String> text2 = new TextField<String>("nomecidade", new PropertyModel<String>(novoAfastamento,"nome_cidade"));
		DateTextField text3 = new DateTextField("datainicio_afast",new PropertyModel<Date>(this, "date"));
		DateTextField text4 = new DateTextField("datafim_afast",new PropertyModel<Date>(this, "date2"));
		DateTextField text5 = new DateTextField("datainicio_evento",new PropertyModel<Date>(this, "date3"));
		DateTextField text6 = new DateTextField("datafim_evento",new PropertyModel<Date>(this, "date4"));
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
		DatePicker datePicker3 = new DatePicker() {
            
			private static final long serialVersionUID = 1L;

			@Override
            protected String getAdditionalJavaScript()
            {
                return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
            }
        };
        datePicker3.setShowOnFieldClick(true);
        datePicker3.setAutoHide(true);
		DatePicker datePicker4 = new DatePicker() {
            
			private static final long serialVersionUID = 1L;

			@Override
            protected String getAdditionalJavaScript()
            {
                return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
            }
        };
        datePicker4.setShowOnFieldClick(true);
        datePicker4.setAutoHide(true);
        text3.add(datePicker);
        text4.add(datePicker2);
        text5.add(datePicker3);
        text6.add(datePicker4);
		DropDownChoice<TipoAfastamento> text7 = new DropDownChoice<TipoAfastamento>("tipoAfastamento",new PropertyModel<TipoAfastamento>(novoAfastamento,"tipoAfastamento"),Arrays.asList(TipoAfastamento.values()));
		DropDownChoice<Onus> text8 = new DropDownChoice<Onus>("tipoOnus",new PropertyModel<Onus>(novoAfastamento,"onus"),Arrays.asList(Onus.values()));
		TextArea<String> text9 = new TextArea<String>("motivo_afast", new PropertyModel<String>(novoAfastamento,"motivo_afast"));

        Button button = new Button("button") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {

				try {					
					if(afastControle.salva(novoAfastamento.getNome_evento(), novoAfastamento.getNome_cidade(),novoAfastamento.getTipoAfastamento(),novoAfastamento.getOnus(),text3.getValue(),text4.getValue(),text5.getValue(),text6.getValue(),novoAfastamento.getMotivo_afast())) {
						setResponsePage(AfastamentoBuscar.class);
					} else {
						params.add("mensagem",afastControle.getNotificacao());
						setResponsePage(AfastamentoCadastro.class);
						}
				
				} catch (Exception e) {
					e.printStackTrace();
					}
			}
		
		
		};
			
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		if(!(p.isEmpty())) {
			error(p.get("mensagem"));
	    }
			
		add(form);
		form.add(feedbackPanel);
		form.add(text);
		form.add(text2);
		form.add(text3);
		form.add(text4);
		form.add(text5);
		form.add(text6);
		form.add(text7);
		form.add(text8);
		form.add(text9);
		form.add(button);
    
	}
	        
}
