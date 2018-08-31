package br.ufes.scap.secretaria.controle;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.wicket.markup.html.form.upload.FileUpload;


import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Documento;
import br.ufes.scap.secretaria.aplicacao.AplDocumento;

@Stateless
public class DocumentoController {

	private String idAfastamento;
	
	@Inject
	private AplDocumento aplDocumento;	
	
	public void salva(Afastamento afastamento,FileUpload file,String name) throws IOException, ParseException{
		
		Documento documento = new Documento();
		Calendar cal = Calendar.getInstance();
		
		documento.setData_juntada(cal);
		documento.setAfastamento(afastamento);
		documento.setTituloDocumento(name);
		documento.setNomeArquivo(file.getClientFileName());
		
		if (!(file.getSize() == 0)) {
            byte[] bytes = file.getBytes();
            documento.setContent(bytes);
        }
		
		aplDocumento.salvar(documento);		
	}

	public String getIdAfastamento() {
		return idAfastamento;
	}

	public void setIdAfastamento(String idAfastamento) {
		this.idAfastamento = idAfastamento;
	}
	
}