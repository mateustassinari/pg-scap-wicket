package br.ufes.scap.nucleo.visao;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.controle.AfastamentoController;
import br.ufes.scap.nucleo.controle.DocumentoLista;
import br.ufes.scap.nucleo.controle.ParecerController;
import br.ufes.scap.nucleo.controle.RelatorController;
import br.ufes.scap.secretaria.controle.DocumentoController;
import br.ufes.scap.secretaria.visao.DocumentoCadastro;

public class AfastamentoMostrar extends TemplatePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private AfastamentoController afastControle;
	
	@Inject
	private RelatorController relatorControle;
	
	@Inject
	private ParecerController parecerControle;
	
	@Inject
	private DocumentoController documentoControle;
	
	private WebMarkupContainer containerTable;

	public AfastamentoMostrar (PageParameters p) {
		
		List<String> dados = afastControle.buscar();
        PageParameters params = new PageParameters();
		
		if(dados == null) {							
			params.add("mensagem",afastControle.getNotificacao());
			setResponsePage(AfastamentoBuscar.class,params);
			return;
		}
		
        add(new Label("nome",dados.get(0)));
        add(new Label("matricula",dados.get(1)));
        add(new Label("email",dados.get(2)));
        add(new Label("tel",dados.get(3)));
        add(new Label("nome_evento",dados.get(5)));
        add(new Label("cidade",dados.get(4)));
        add(new Label("data_inicio",dados.get(11)));
        add(new Label("data_fim",dados.get(12)));
        add(new Label("status",dados.get(8)));
        add(new Label("tipo",dados.get(6)));
        add(new Label("onus",dados.get(7)));
        add(new Label("data_inicio_afast",dados.get(9)));
        add(new Label("data_fim_afast",dados.get(10)));

        relatorControle.setIdAfastamento(afastControle.getIdAfastamento());
        parecerControle.setIdAfastamento(afastControle.getIdAfastamento());
        documentoControle.setIdAfastamento(afastControle.getIdAfastamento());
        
        add(new BookmarkablePageLink<Void>("mudar_status", MudarStatus.class));   
        add(new BookmarkablePageLink<Void>("deferir_parecer", ParecerCadastro.class));
        add(new BookmarkablePageLink<Void>("relator", RelatorCadastro.class));   
        add(new BookmarkablePageLink<Void>("documento", DocumentoCadastro.class));   
        add(new BookmarkablePageLink<Void>("ver_parecer", ParecerMostrar.class));   

        List<DocumentoLista> tabela = afastControle.getListaDocumento();
		
		containerTable = new WebMarkupContainer("containerTable");
		containerTable.setOutputMarkupId(true);
		WebMarkupContainer colunas = new WebMarkupContainer("colunas") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onConfigure() {
				super.onConfigure();
				setVisible(!tabela.isEmpty());
			}
		};
		containerTable.add(colunas);
		ListView<DocumentoLista> documentoListView = new ListView<DocumentoLista>("documentos", tabela) {
		
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<DocumentoLista> item) {
				item.add(new Label("Titulo", new PropertyModel<>(item.getModel(), "titulo")));
				item.add(new Label("Data_juntada", new PropertyModel<>(item.getModel(), "juntada")));
				item.add(createButtonDownload("Download",item.getModelObject()));

			}

		}.setReuseItems(true);
		containerTable.add(documentoListView);
		documentoListView.setVisible(!tabela.isEmpty());
		WebMarkupContainer containerNoResult = new WebMarkupContainer("noResult") {
		
			private static final long serialVersionUID = 1L;

			protected void onConfigure() {
				super.onConfigure();
				setVisible(tabela.isEmpty());
			};
		};
		containerTable.add(containerNoResult);
		add(containerTable);
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
		if(!(p.isEmpty())) {
        	error(p.get("mensagem"));
        }

	}

	private Component createButtonDownload(String id, DocumentoLista documento) {
		return new AjaxLink<String>(id) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
			}
		};	
	
	}
	
}
