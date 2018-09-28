package br.ufes.scap.nucleo.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Documento;
import br.ufes.scap.nucleo.dominio.Onus;
import br.ufes.scap.nucleo.dominio.TipoAfastamento;
import br.ufes.scap.secretaria.aplicacao.AplDocumento;
import br.ufes.scap.secretaria.aplicacao.AplMandato;

@Stateless
public class AfastamentoController {

	private String idAfastamento;
	
	private List<DocumentoLista> listaDocumento;
	
	private String notificacao = "";
	
	@Inject
	private AplAfastamento aplAfastamento;

	@Inject
	private AplDocumento aplDocumento;

	@Inject
	private AplMandato aplMandato;
	
	@Inject
	private Usuario usuarioWeb;
	
	public boolean salva(String nome_evento, String nome_cidade, TipoAfastamento tipo, Onus onusAfastamento, String data_iniAfast, String data_fimAfast, String data_iniEvento, String data_fimEvento, String motivo_afast) throws ParseException {
	
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yy");

		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal3 = Calendar.getInstance();
		Calendar cal4 = Calendar.getInstance();
		Calendar cal5 = Calendar.getInstance();
		
		cal2.setTime(formatada.parse(data_iniAfast));
		cal3.setTime(formatada.parse(data_fimAfast));
		cal4.setTime(formatada.parse(data_iniEvento));
		cal5.setTime(formatada.parse(data_fimEvento));

		if(cal2.after(cal3) || cal4.after(cal5)) {
			notificacao = "Data de início não pode ser superior a Data final";							
			return false;
		}

		Afastamento novoAfastamento = new Afastamento();
		novoAfastamento.setNome_evento(nome_evento);
		novoAfastamento.setNome_cidade(nome_cidade);
		novoAfastamento.setData_criacao(cal);
		novoAfastamento.setData_iniAfast(cal2);
		novoAfastamento.setData_fimAfast(cal3);
		novoAfastamento.setData_iniEvento(cal4);
		novoAfastamento.setData_fimEvento(cal5);
		novoAfastamento.setMotivo_afast(motivo_afast);
		aplAfastamento.salvar(novoAfastamento,usuarioWeb.getLogado(), tipo, onusAfastamento,motivo_afast);
		return true;

	}	
		
	public List<AfastamentoLista> mostrar() {
		
		List<Afastamento> listaDAO = aplAfastamento.listaAfastamentos();
		List<AfastamentoLista> tabela = new ArrayList<AfastamentoLista>();
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yy");

		
		for (Integer i = 0; i < listaDAO.size(); i++) {
			if (!listaDAO.get(i).getSituacaoSolicitacao().getStatusAfastamento().equals("CANCELADO") && !listaDAO.get(i).getSituacaoSolicitacao().getStatusAfastamento().equals("ARQUIVADO")) {
				AfastamentoLista elemento = new AfastamentoLista();
				elemento.setId_afastamento(listaDAO.get(i).getId_afastamento().toString());
				elemento.setNome_pessoa(listaDAO.get(i).getSolicitante().getNome() + " " + listaDAO.get(i).getSolicitante().getSobreNome());
				elemento.setNome_cidade(listaDAO.get(i).getNome_cidade());
				elemento.setNome_evento(listaDAO.get(i).getNome_evento());
				elemento.setSituacaoSolicitacao(listaDAO.get(i).getSituacaoSolicitacao().getStatusAfastamento());
				elemento.setTipoAfastamento(listaDAO.get(i).getTipoAfastamento().getTipoAfastamento());
				elemento.setData_iniAfast(formatada.format(listaDAO.get(i).getData_iniAfast().getTime()));
				elemento.setData_fimAfast(formatada.format(listaDAO.get(i).getData_fimAfast().getTime()));
				tabela.add(elemento);
		
			}
		}

		return tabela;
	}
	
	
	public List<String> buscar() {
	
		Afastamento afastamento = aplAfastamento.buscaId(idAfastamento);
		
		if(afastamento == null){
			notificacao = "Afastamento inexistente";
			return null;
		}
	
		DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
		List<DocumentoLista> tabela = new ArrayList<DocumentoLista>();
		List<Documento> listaDAO = aplDocumento.buscaPorAfastamento(afastamento.getId_afastamento().toString());
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yy");

		for (Integer i = 0; i < listaDAO.size(); i++) {
			DocumentoLista elemento = new DocumentoLista();
			elemento.setId(listaDAO.get(i).getId_documento().toString());
			elemento.setTitulo(listaDAO.get(i).getTituloDocumento());
			elemento.setArquivo(listaDAO.get(i).getNomeArquivo());
			elemento.setJuntada(formatada.format(listaDAO.get(i).getData_juntada().getTime()));
			elemento.setContent(listaDAO.get(i).getContent());
			tabela.add(elemento);

		}

		this.listaDocumento = tabela;

		List<String> dadosAfastamento = new ArrayList<String>();
		dadosAfastamento.add(afastamento.getSolicitante().getNome() + " " + afastamento.getSolicitante().getSobreNome());
		dadosAfastamento.add(afastamento.getSolicitante().getMatricula());
		dadosAfastamento.add(afastamento.getSolicitante().getEmail());
		dadosAfastamento.add(afastamento.getSolicitante().getTelefone());
		dadosAfastamento.add(afastamento.getNome_cidade());
		dadosAfastamento.add(afastamento.getNome_evento());
		dadosAfastamento.add(afastamento.getTipoAfastamento().getTipoAfastamento());
		dadosAfastamento.add(afastamento.getOnus().getOnus());
		dadosAfastamento.add(afastamento.getSituacaoSolicitacao().getStatusAfastamento());
		dadosAfastamento.add(f.format(afastamento.getData_iniAfast().getTime()));
		dadosAfastamento.add(f.format(afastamento.getData_fimAfast().getTime()));
		dadosAfastamento.add(f.format(afastamento.getData_iniEvento().getTime()));
		dadosAfastamento.add(f.format(afastamento.getData_fimEvento().getTime()));
		dadosAfastamento.add(afastamento.getId_afastamento().toString());

		return dadosAfastamento;
	}
	
	public boolean mudarStatus(String novoStatus){
		
		Afastamento afastamento = aplAfastamento.buscaId(idAfastamento);
		
		if(novoStatus.equals("CANCELADO")){
			if((usuarioWeb.getMatricula().equals(afastamento.getSolicitante().getMatricula())) || (aplMandato.checaMandato(usuarioWeb.getLogado().getId().toString()))){
				aplAfastamento.mudarStatus(afastamento,novoStatus,usuarioWeb.getLogado());
				return true;
			}else{
				notificacao = "Somente o solicitante pode cancelar o pedido de afastamento ou o chefe do departamento";
				return false;
			}
		}
		
		if(novoStatus.equals("ARQUIVADO")){
			if(afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("REPROVADO")) {
				aplAfastamento.mudarStatus(afastamento,novoStatus,usuarioWeb.getLogado());
				return true;
			}	
			if(afastamento.getTipoAfastamento().getTipoAfastamento().equals("NACIONAL")) {
				if(!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("APROVADO_DI")) {
					notificacao = "Pedido de afastamento ainda não foi concluído";
					return false;
				}
			} else {
					if(!afastamento.getSituacaoSolicitacao().getStatusAfastamento().equals("APROVADO_PRPPG")) {
						notificacao = "Pedido de afastamento ainda não foi concluído";
						return false;
					}
				}
		}	
		
		if(novoStatus.equals("APROVADO_DI")){
			if(afastamento.getTipoAfastamento().getTipoAfastamento().equals("INTERNACIONAL")) {
				notificacao = "Somente o relator pode aprovar esse afastamento";
				return false;
			} 
		}
		
		aplAfastamento.mudarStatus(afastamento,novoStatus,usuarioWeb.getLogado());
		return true;
	}
	
	
	public String getIdAfastamento() {
		return idAfastamento;
	}

	public void setIdAfastamento(String idAfastamento) {
		this.idAfastamento = idAfastamento;
	}

	public List<DocumentoLista> getListaDocumento() {
		return listaDocumento;
	}

	public void setListaDocumento(List<DocumentoLista> listaDocumento) {
		this.listaDocumento = listaDocumento;
	}

	public String getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(String notificacao) {
		this.notificacao = notificacao;
	}

	
	
}
