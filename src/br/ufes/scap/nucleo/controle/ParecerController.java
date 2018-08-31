package br.ufes.scap.nucleo.controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.aplicacao.AplAfastamento;
import br.ufes.scap.nucleo.aplicacao.AplParecer;
import br.ufes.scap.nucleo.aplicacao.Usuario;
import br.ufes.scap.nucleo.dominio.Afastamento;
import br.ufes.scap.nucleo.dominio.Parecer;
import br.ufes.scap.nucleo.dominio.TipoParecer;

@Stateless
public class ParecerController {

	private String idAfastamento;
		
	@Inject
	private Usuario usuarioWeb;
	
	@Inject
	private AplParecer	aplParecer;
	
	@Inject
	private AplAfastamento aplAfastamento;
	
	public List<ParecerLista> listar(){
		
		List<Parecer> listaDAO = aplParecer.buscaPorAfastamento(idAfastamento);
		List<ParecerLista> tabela = new ArrayList<ParecerLista>();
		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yy");
		for(Integer i=0;i<listaDAO.size();i++){
			ParecerLista elemento = new ParecerLista();
			elemento.setNomeCriador(listaDAO.get(i).getRelator().getNome()+" "+listaDAO.get(i).getRelator().getSobreNome());
			elemento.setData(formatada.format(listaDAO.get(i).getData_parecer().getTime()));
			elemento.setJulgamento(listaDAO.get(i).getJulgamento().get());
			elemento.setMotivo(listaDAO.get(i).getMotivoIndeferimento());
			tabela.add(elemento);
		}
		return tabela;
	}
	
	public boolean salvar(String motivo, TipoParecer tipoParecer){
		
		Afastamento afastamento = new Afastamento();
		afastamento = aplAfastamento.buscaId(idAfastamento);
		Parecer parecer = new Parecer();
					
		Calendar cal = Calendar.getInstance();
		
		parecer.setMotivoIndeferimento(motivo);
		parecer.setJulgamento(tipoParecer);
		parecer.setRelator(usuarioWeb.getLogado());
		parecer.setAfastamento(afastamento);
		parecer.setData_parecer(cal);
		
		aplParecer.salvar(parecer,afastamento,usuarioWeb.getLogado(),tipoParecer);
		return true;
	}

	public String getIdAfastamento() {
		return idAfastamento;
	}

	public void setIdAfastamento(String idAfastamento) {
		this.idAfastamento = idAfastamento;
	}

	
}
