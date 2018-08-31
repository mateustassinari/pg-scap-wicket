package br.ufes.scap.nucleo.dominio;

import java.util.Calendar;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Parecer.class)
public class Parecer_ {
	public static volatile SingularAttribute<Parecer, Long> id_parecer;
	public static volatile SingularAttribute<Parecer, Pessoa> relator;
	public static volatile SingularAttribute<Parecer, Afastamento> afastamento;
	public static volatile SingularAttribute<Parecer, Calendar> data_parecer;
	public static volatile SingularAttribute<Parecer, TipoParecer> julgamento;
	public static volatile SingularAttribute<Parecer, String> motivoIndeferimento;
}