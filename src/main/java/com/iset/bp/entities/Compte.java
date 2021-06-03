	package com.iset.bp.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
 

@Entity
//hertitage
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",
discriminatorType=DiscriminatorType.STRING,length=4)
public class Compte implements Serializable {
	@Id
	   private String numcompte;
	   private Date datecreation;
	   private double solde;
	   
	   @ManyToOne
	   @JoinColumn(name="CODE_CLI")
	   Client client;
	 
	@OneToMany(mappedBy="compte")
	private List<Operation> oper = new ArrayList<Operation>();
	
	
	
	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Compte(String numcompte, Date datecreation, double solde, Client client) {
		super();
		this.numcompte = numcompte;
		this.datecreation = datecreation;
		this.solde = solde;
		this.client = client;
	}

	public Client getCl() {
		return client;
	}


	public void setCl(Client cl) {
		this.client = cl;
	}


	public Collection<Operation> getOper() {
		return oper;
	}


	


	public String getNumcompte() {
		return numcompte;
	}

	public void setNumcompte(String numcompte) {
		this.numcompte = numcompte;
	}

	public Date getDatecreation() {
		return datecreation;
	}


	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}


	


	public void setOper(List<Operation> oper) {
		this.oper = oper;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}

	

	
	
}
