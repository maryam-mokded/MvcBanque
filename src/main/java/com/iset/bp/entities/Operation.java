package com.iset.bp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_oper",discriminatorType=DiscriminatorType.STRING,length=4)
public class Operation implements Serializable{

	@Id
	@GeneratedValue
	private Long numoperation;
	private Date dateoperation;
	private double montant;
	
	@ManyToOne
	private Compte compte;
	
	
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Operation(Date dateoperation, double montant, Compte compte) {
		super();
		this.dateoperation = dateoperation;
		this.montant = montant;
		this.compte = compte;
	}


	public Long getNumoperation() {
		return numoperation;
	}
	public void setNumoperation(Long numoperation) {
		this.numoperation = numoperation;
	}
	public Date getDateoperation() {
		return dateoperation;
	}
	public void setDateoperation(Date dateoperation) {
		this.dateoperation = dateoperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
	
	
}
