package com.iset.bp.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{

	private double taux;

	
	
	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(String numcompte, Date datecreation, double solde, Client client,double taux) {
		super(numcompte, datecreation, solde,client);
		this.taux = taux;
		// TODO Auto-generated constructor stub
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	

}
