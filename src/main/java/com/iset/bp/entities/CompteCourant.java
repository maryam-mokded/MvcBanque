package com.iset.bp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{

	private double decouvert;

	
	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteCourant(String numcompte, Date datecreation, double solde, Client client,double decouvert) {
		super(numcompte, datecreation, solde,client);
		this.decouvert = decouvert;
		// TODO Auto-generated constructor stub
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	
}
