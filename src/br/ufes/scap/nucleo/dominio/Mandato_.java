package br.ufes.scap.nucleo.dominio;

import java.util.Calendar;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Mandato.class)
public class Mandato_ {
	public static volatile SingularAttribute<Mandato, Long> id_mandato;
	public static volatile SingularAttribute<Mandato, Pessoa> pessoa;
	public static volatile SingularAttribute<Mandato, Calendar> data_inicio;
	public static volatile SingularAttribute<Mandato, Calendar> data_fim;
}