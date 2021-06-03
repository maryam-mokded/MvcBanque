package com.iset.bp.metier;

import org.springframework.data.domain.Page;

import com.iset.bp.entities.Compte;
import com.iset.bp.entities.Operation;

public interface IBanqueMetier {

	public Compte getCompte(String codeCompte);
	public void versement(String codeCompte,double montant);
	public void retrait(String codeCompte,double montant);
	public void virement(String codeCompteRetrait,String codeCompteVersement,double montant);
	public Page<Operation> listOperationCompte(String codeCompte,int page,int sizePage);
	
	
}
