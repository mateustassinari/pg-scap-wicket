package br.ufes.scap.secretaria.visao;

import java.io.File;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Bytes;

import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Documento;
import br.ufes.scap.nucleo.visao.AfastamentoBuscar;
import br.ufes.scap.nucleo.visao.TemplatePage;
import br.ufes.scap.secretaria.controle.DocumentoController;

public class DocumentoCadastro extends TemplatePage {

	private static final long serialVersionUID = 1L;

	private FileUploadField fileUpload;

	@Inject
	private DocumentoController documentoControle;
	
	@Inject
	private AplAfastamento aplAfastamento;
	
	public DocumentoCadastro() {
		
		Documento documento = new Documento();
		Form<Object> form = new Form<Object>("form");
		TextField<String> text = new TextField<String>("titulo", new PropertyModel<String>(documento,"tituloDocumento"));
		fileUpload = new FileUploadField("file");
		form.setMultiPart(true);
		form.setMaxSize(Bytes.kilobytes(10000));
		
		Button button = new Button("button") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				
				Afastamento afastamento = new Afastamento();
				afastamento = aplAfastamento.buscaId(documentoControle.getIdAfastamento());
				FileUpload uploadedFile = fileUpload.getFileUpload();
				if (uploadedFile != null) {
					File newFile = new File("C:\\dev\\" + uploadedFile.getClientFileName());
					if (newFile.exists()) {
						newFile.delete();
					}
					try {
						newFile.createNewFile();
						uploadedFile.writeTo(newFile);
						documentoControle.salva(afastamento,uploadedFile, documento.getTituloDocumento());
						
					} catch (Exception e) {
						throw new IllegalStateException("Error");
					}
				}
				setResponsePage(AfastamentoBuscar.class);
			}
	
		};

		add(form);
		form.add(text);
		form.add(fileUpload);
		form.add(button);
		
	}
	
}