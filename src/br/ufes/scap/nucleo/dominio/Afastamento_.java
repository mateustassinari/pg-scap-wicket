package br.ufes.scap.nucleo.dominio;

import java.util.Calendar;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Afastamento.class)
public class Afastamento_ {
	public static volatile SingularAttribute<Afastamento, Long> id_afastamento;
	public static volatile SingularAttribute<Afastamento, Pessoa> solicitante;
	public static volatile SingularAttribute<Afastamento, SituacaoSolic> situacaoSolicitacao;
	public static volatile SingularAttribute<Afastamento, TipoAfastamento> tipoAfastamento;
	public static volatile SingularAttribute<Afastamento, Onus> onus;
	public static volatile SingularAttribute<Afastamento, Calendar> data_criacao;
	public static volatile SingularAttribute<Afastamento, Calendar> data_iniAfast;
	public static volatile SingularAttribute<Afastamento, Calendar> data_fimAfast;
	public static volatile SingularAttribute<Afastamento, Calendar> data_inievento;
	public static volatile SingularAttribute<Afastamento, Calendar> data_fimevento;
	public static volatile SingularAttribute<Afastamento, String> nome_evento;
	public static volatile SingularAttribute<Afastamento, String> nome_cidade;
	public static volatile SingularAttribute<Afastamento, String> motivo_afast;
	public static volatile SingularAttribute<Afastamento, String> motivo_cancel;
	public static volatile SetAttribute<Afastamento, Documento> documentos;
	public static volatile SetAttribute<Afastamento, Parecer> pareceres;
}