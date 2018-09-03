package br.ufes.scap.nucleo.visao;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.aplicacao.AplRelator;
import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.controle.ParecerController;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Parecer;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.dominio.Relator;
import br.ufes.scap.nucleo.dominio.TipoParecer;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

public class ParecerCadastro extends TemplatePage {

	private static final long serialVersionUID = 1L;

    @Inject
	private Usuario usuarioWeb;	
    
    @Inject
	private AplPessoa aplPessoa;
    
    @Inject
	private ParecerController parecerControle;
    
    @Inject
    private AplAfastamento aplAfastamento;
    
    @Inject
    private AplRelator aplRelator;
    
	public ParecerCadastro() {
		
		Pessoa pessoa_aux = new Pessoa();
		Afastamento afastamento = new Afastamento();
		Relator relator = new Relator();
        PageParameters params = new PageParameters();
        pessoa_aux = aplPessoa.buscaMatricula(usuarioWeb.getMatricula());
    	afastamento = aplAfastamento.buscaId(parecerControle.getIdAfastamento());
    	relator = aplRelator.buscaPorAfastamento(parecerControle.getIdAfastamento());
    	
    	if(!parecerControle.verifica(pessoa_aux, afastamento, relator)) {
    		params.add("mensagem",parecerControle.getNotificacao());
        	setResponsePage(AfastamentoMostrar.class,params);
    	}
    	
		Parecer novoParecer = new Parecer();
		Form<Object> form = new Form<Object>("form");
		TextArea<String> text = new TextArea<String>("motivo", new PropertyModel<String>(novoParecer,"motivoIndeferimento"));
		DropDownChoice<TipoParecer> text2 = new DropDownChoice<TipoParecer>("tipoJulgamento",new PropertyModel<TipoParecer>(novoParecer,"julgamento"),Arrays.asList(TipoParecer.values()));
		Button button = new Button("button") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				
				if(parecerControle.salvar(novoParecer.getMotivoIndeferimento(), novoParecer.getJulgamento())) {
					setResponsePage(AfastamentoBuscar.class);
				} 
			}
		};

		add(form);
		form.add(text);
		form.add(text2);
		form.add(button);

	}
}
