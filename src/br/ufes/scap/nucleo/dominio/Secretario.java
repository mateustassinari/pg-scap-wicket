package br.ufes.scap.nucleo.dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Secretario extends Pessoa {

	private static final long serialVersionUID = 1L;	

}