package br.ufes.scap.nucleo.dominio;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Parentesco.class)
public class Parentesco_ {
	public static volatile SingularAttribute<Parentesco, Long> id_parentesco;
	public static volatile SingularAttribute<Parentesco, Pessoa> pessoa1;
	public static volatile SingularAttribute<Parentesco, Pessoa> pessoa2;
	public static volatile SingularAttribute<Parentesco, TipoParentesco> tipoParentesco;
}