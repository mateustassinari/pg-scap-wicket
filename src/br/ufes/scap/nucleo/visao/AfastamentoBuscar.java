package br.ufes.scap.nucleo.visao;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.controle.AfastamentoController;
import br.ufes.scap.nucleo.controle.AfastamentoLista;
import br.ufes.scap.nucleo.dominio.Afastamento;

public class AfastamentoBuscar extends TemplatePage{

	private static final long serialVersionUID = 1L;

	@Inject
	private AfastamentoController afastControle;
	
	private WebMarkupContainer containerTable;
	
	public AfastamentoBuscar(PageParameters p) {

		Afastamento novoAfastamento = new Afastamento();
		Form<Object> form = new Form<Object>("form");
		TextField<String> text = new TextField<String>("id", new PropertyModel<String>(novoAfastamento,"id_afastamento"));
		Button button = new Button("button") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {

				afastControle.setIdAfastamento(novoAfastamento.getId_afastamento().toString());
				setResponsePage(AfastamentoMostrar.class);
		
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
		
		List<AfastamentoLista> tabela = afastControle.mostrar();
				
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
		ListView<AfastamentoLista> afastamentoListView = new ListView<AfastamentoLista>("afastamentos", tabela) {
		
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<AfastamentoLista> item) {
				item.add(new Label("Nome_solicitante", new PropertyModel<>(item.getModel(), "nome_pessoa")));
				item.add(new Label("ID_afastamento", new PropertyModel<>(item.getModel(), "id_afastamento")));
				item.add(new Label("Nome_evento", new PropertyModel<>(item.getModel(), "nome_evento")));
				item.add(new Label("Status", new PropertyModel<>(item.getModel(), "situacaoSolicitacao")));
				item.add(new Label("Data_inicio", new PropertyModel<>(item.getModel(), "data_iniAfast")));
				item.add(new Label("Data_fim", new PropertyModel<>(item.getModel(), "data_fimAfast")));
				item.add(createButtonVer("Ver",item.getModelObject()));

			}

		}.setReuseItems(true);
		containerTable.add(afastamentoListView);
		afastamentoListView.setVisible(!tabela.isEmpty());
		WebMarkupContainer containerNoResult = new WebMarkupContainer("noResult") {
			
			private static final long serialVersionUID = 1L;

			protected void onConfigure() {
				super.onConfigure();
				setVisible(tabela.isEmpty());
			};
		};
		containerTable.add(containerNoResult);
		add(containerTable);
		

	}

	private Component createButtonVer(String id, AfastamentoLista afastamento) {
		return new AjaxLink<String>(id) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				AfastamentoLista aux = afastamento;
				afastControle.setIdAfastamento(aux.getId_afastamento());
				setResponsePage(AfastamentoMostrar.class);
			}
		};	
	}
}