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
import br.ufes.scap.secretaria.aplicacao.AplMandato;
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
    
    @Inject
    private AplMandato aplMandato;
	
	public ParecerCadastro() {
		
		Pessoa pessoa_aux = new Pessoa();
		Afastamento afastamento = new Afastamento();
		Relator relator = new Relator();
        PageParameters params = new PageParameters();
        pessoa_aux = aplPessoa.buscaMatricula(usuarioWeb.getMatricula());
    	afastamento = aplAfastamento.buscaId(parecerControle.getIdAfastamento());
    	relator = aplRelator.buscaPorAfastamento(parecerControle.getIdAfastamento());
    	if(!(pessoa_aux.getTipoPessoa().equals("1"))) {
        	if(afastamento.getTipoAfastamento().getTipoAfastamento().equals("INTERNACIONAL")) {  
        		if((!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("APROVADO_DI")) && (!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("APROVADO_CT"))){
        			String info = "O afastamento não se encontra no status: APROVADO_DI ou APROVADO_CT";
        			params.add("mensagem",info);
        			setResponsePage(AfastamentoMostrar.class,params);
        			return;
        		}
        	} else {
        		String info = "Você não pode deferir um parecer para esse tipo de afastamento";
    			params.add("mensagem",info);
    			setResponsePage(AfastamentoMostrar.class,params);
    			return;
        		}
     	}

    	if(afastamento.getSolicitante().getMatricula().equals(pessoa_aux.getMatricula())) {
    		String info = "Você não pode deferir um parecer para o seu próprio afastamento";
    		params.add("mensagem",info);
		    setResponsePage(AfastamentoMostrar.class,params);
		    return;
    	}
    	
    	if(afastamento.getTipoAfastamento().getTipoAfastamento().equals("INTERNACIONAL")) {
    		if(!aplMandato.checaMandato(pessoa_aux.getId_pessoa().toString())){
    			if(!pessoa_aux.getTipoPessoa().equals("2")) {
    				if(!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("LIBERADO")){
    					String info = "O afastamento não se encontra no status: LIBERADO";
    					params.add("mensagem",info);
    					setResponsePage(AfastamentoMostrar.class,params);
    					return;
    				}
    				if((!(relator.getRelator().getMatricula().equals(pessoa_aux.getMatricula())))) {
    					String info2 = "Somente o relator escolhido pode deferir um parecer para esse afastamento";
    					params.add("mensagem",info2);
    					setResponsePage(AfastamentoMostrar.class,params);
    					return;
    				}
    			}
        	}
    	} else {
    			if(!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("LIBERADO")){
    				String info = "O afastamento não se encontra no status: LIBERADO";
    				params.add("mensagem",info);
    				setResponsePage(AfastamentoMostrar.class,params);
    				return;
    			}
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
