package br.ufes.scap.nucleo.dominio;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Relator.class)
public class Relator_ {
	public static volatile SingularAttribute<Relator, Long> id_relator;
	public static volatile SingularAttribute<Relator, Pessoa> relator;
	public static volatile SingularAttribute<Relator, Afastamento> afastamento;
}