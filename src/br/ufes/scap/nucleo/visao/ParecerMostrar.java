package br.ufes.scap.nucleo.visao;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import br.ufes.scap.nucleo.controle.ParecerController;
import br.ufes.scap.nucleo.controle.ParecerLista;

public class ParecerMostrar extends TemplatePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParecerController parecerControle;
	
	private WebMarkupContainer containerTable;
	
	public ParecerMostrar() {
		
		List<ParecerLista> tabela = parecerControle.listar();
		
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
		ListView<ParecerLista> parecerListView = new ListView<ParecerLista>("pareceres", tabela) {
		
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<ParecerLista> item) {
				item.add(new Label("Nome", new PropertyModel<>(item.getModel(), "nomeCriador")));
				item.add(new Label("Data", new PropertyModel<>(item.getModel(), "data")));
				item.add(new Label("Julgamento", new PropertyModel<>(item.getModel(), "julgamento")));
				item.add(new Label("Motivo", new PropertyModel<>(item.getModel(), "motivo")));
			}

		}.setReuseItems(true);
		containerTable.add(parecerListView);
		parecerListView.setVisible(!tabela.isEmpty());
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

}