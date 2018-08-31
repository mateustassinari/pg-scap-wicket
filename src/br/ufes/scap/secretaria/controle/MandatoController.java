package br.ufes.scap.secretaria.controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufes.scap.nucleo.dominio.Mandato;
import br.ufes.scap.nucleo.dominio.Pessoa;
import br.ufes.scap.secretaria.aplicacao.AplMandato;
import br.ufes.scap.secretaria.aplicacao.AplPessoa;

@Stateless
public class MandatoController {

	private String notificacao = "";
	
	@Inject
	private AplMandato aplMandato;
	
	@Inject
    private AplPessoa aplPessoa;
		
	public boolean salva(String matricula, String data_iniMandato, String data_fimMandato) throws ParseException {
		
		Pessoa pessoa = new Pessoa();
		List<Mandato> mandato = new ArrayList<Mandato>();				
		pessoa = aplPessoa.buscaMatricula(matricula);
		mandato = aplMandato.busca();
		if(pessoa == null) {
			notificacao = "Matrícula não existe";							
			return false;
		}
		
		if(pessoa.getTipoPessoa().equals("1")) {				

			SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yy");
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.setTime(formatada.parse(data_iniMandato));
			cal2.setTime(formatada.parse(data_fimMandato));

			if(cal.after(cal2)) {
				notificacao = "Data de início não pode ser superior a Data final";							
				return false;
			}

			if(!(mandato.isEmpty())) {
				if(cal.before(mandato.get(0).getData_fim()) || cal.equals(mandato.get(0).getData_fim())) {									
					notificacao = "Já existe um mandato cadastrado";
					return false;
				}
			}

			Mandato novoMandato = new Mandato();			
			novoMandato.setData_inicio(cal);
			novoMandato.setData_fim(cal2);
			aplMandato.salvar(novoMandato, matricula);
			return true;
			
		} else {
			notificacao = "Somente professores podem ser chefes do departamento";
			return false;
		}
		
	}

	public String getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(String notificacao) {
		this.notificacao = notificacao;
	}
	
}