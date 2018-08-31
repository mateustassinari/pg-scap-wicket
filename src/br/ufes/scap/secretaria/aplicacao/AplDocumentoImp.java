package br.ufes.scap.secretaria.aplicacao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Documento;
import br.ufes.scap.secretaria.persistencia.DocumentoDAO;

@Stateless
public class AplDocumentoImp implements AplDocumento{

	@Inject
	private DocumentoDAO documentoDAO;
	
	@Override
	public void salvar(Documento documento) {
		documentoDAO.salvar(documento);	
	}

	@Override
	public Documento buscaId(String id_documento) {
		Documento documento = documentoDAO.buscaId(id_documento);
		return documento;
	}

	@Override
	public List<Documento> buscaPorAfastamento(String id_afastamento) {
		return documentoDAO.buscaPorAfastamento(id_afastamento);
	}

}