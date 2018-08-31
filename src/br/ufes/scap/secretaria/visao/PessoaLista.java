package br.ufes.scap.secretaria.visao;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.nucleo.visao.TemplatePage;
import br.ufes.scap.secretaria.controle.ParentescoController;
import br.ufes.scap.secretaria.controle.PessoaController;

public class PessoaLista extends TemplatePage {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaController pessoaControle;
	
	@Inject
	private ParentescoController parentescoControle;
	
	private WebMarkupContainer containerTable;
	
	public PessoaLista(PageParameters p) {

		List<Pessoa> tabela = pessoaControle.getListaPessoa();
				
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
		ListView<Pessoa> pessoaListView = new ListView<Pessoa>("pessoas", tabela) {
		
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Pessoa> item) {
				item.add(new Label("Nome", new PropertyModel<>(item.getModel(), "nome")));
				item.add(new Label("Matricula", new PropertyModel<>(item.getModel(), "matricula")));
				item.add(new Label("Email", new PropertyModel<>(item.getModel(), "email")));
				item.add(new Label("Telefone", new PropertyModel<>(item.getModel(), "telefone")));
				item.add(createButtonCadastrar("Cadastrar",item.getModelObject()));
				
			}

		}.setReuseItems(true);
		containerTable.add(pessoaListView);
		pessoaListView.setVisible(!tabela.isEmpty());
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

	private Component createButtonCadastrar(String id, Pessoa pessoa) {
		return new AjaxLink<String>(id) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if(pessoa.getTipoPessoa().equals("1")) {
					parentescoControle.setMatricula1(pessoa.getMatricula());
					setResponsePage(ParentescoCadastro.class);
				} else {
			        PageParameters params = new PageParameters();
					String info = "É possivel cadastrar parentesco somente entre professores";
					params.add("mensagem",info);
					setResponsePage(PessoaLista.class,params);
				}
			}
		};
		
	}		

}