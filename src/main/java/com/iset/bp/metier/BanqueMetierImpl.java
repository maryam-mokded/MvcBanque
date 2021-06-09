package com.iset.bp.metier;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iset.bp.dao.CompteRepository;
import com.iset.bp.dao.OperationRepository;
import com.iset.bp.entities.Compte;
import com.iset.bp.entities.CompteCourant;
import com.iset.bp.entities.CompteEpargne;
import com.iset.bp.entities.Operation;
import com.iset.bp.entities.Retrait;
import com.iset.bp.entities.Versement;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

	@Autowired
	CompteRepository CompteRep;
	
	@Autowired
	OperationRepository OperationRep;
	
	public Compte getCompte(String codeCompte) {
		
		Optional<Compte> cpt=CompteRep.findById(codeCompte);
		if (cpt.isPresent()) { 
			return cpt.get();
		}else throw new RuntimeException("Compte introuvable"); 
		
		/*
		Compte cpt = CompteRep.getById(codeCompte);
		if(cpt == null) throw new RuntimeException("Compte Introuvable !!");
    	else return cpt;*/
	}

	
	public void versement(String codeCompte, double montant) {
		
		Compte cpt = this.getCompte(codeCompte);
		Versement versement = new Versement(new Date(), montant,cpt); 
	    OperationRep.save(versement);
	    
	    cpt.setSolde(cpt.getSolde() + montant);
	    CompteRep.save(cpt);
		
	}

	
	public void retrait(String codeCompte, double montant) {
		Compte compte = this.getCompte(codeCompte);
		double facilitesCaisse = 0;
		double Solde = 0 ;
		if (compte instanceof CompteCourant) {
			facilitesCaisse = ((CompteCourant) compte).getDecouvert();
			 Solde = compte.getSolde()+facilitesCaisse;
		
		}else if (compte instanceof CompteEpargne) {
			 Solde = compte.getSolde() ;
		}
		 
		if (Solde > montant ) {

			Retrait retrait = new Retrait(new Date(), montant,compte);
			OperationRep.save(retrait);
			compte.setSolde(compte.getSolde() - montant);
			CompteRep.save(compte);	
		}else {
			throw new RuntimeException("Solde insuffisant !!");
		  }
		}
	

	

	public void virement(String codeCompteRetrait, String codeCompteVersement, double montant) {
		Compte cpt1 = this.getCompte(codeCompteRetrait);
		Compte cpt2 = this.getCompte(codeCompteVersement);
		if(cpt1 == cpt2) throw new RuntimeException("Meme Compte !!");
		else 
			this.retrait(codeCompteRetrait, montant);
			this.versement(codeCompteVersement, montant);
	}

	
	public Page<Operation> listOperationCompte(String codeCompte, int page, int sizePage) {
		return OperationRep.listOperation(codeCompte, PageRequest.of(page,sizePage,
				Sort.by(("dateoperation")).descending()));
	}

}
