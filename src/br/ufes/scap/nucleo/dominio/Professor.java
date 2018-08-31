package br.ufes.scap.nucleo.dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Professor extends Pessoa {

	private static final long serialVersionUID = 1L;	

}