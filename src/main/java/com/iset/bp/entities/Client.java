package com.iset.bp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_client")
  private Long code;
  private String nom;
  
  @OneToMany(mappedBy="client",fetch=FetchType.LAZY)
  private List<Compte> cpt= new ArrayList<Compte>();

	public Client() {
		super();
		//TODO Auto-generated constructor stub
	}

	public Client(String nom) {
		super();
		this.nom = nom;
	}


	public Long getCode() {
		return code;
	}


	public void setCode(Long code) {
		this.code = code;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Compte> getCpt() {
		return cpt;
	}

	public void setCpt(List<Compte> cpt) {
		this.cpt = cpt;
	}
	
}
