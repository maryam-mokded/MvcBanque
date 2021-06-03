package com.iset.bp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iset.bp.dao.ClientRepository;
import com.iset.bp.dao.CompteRepository;
import com.iset.bp.dao.OperationRepository;
import com.iset.bp.entities.Client;
import com.iset.bp.entities.Compte;
import com.iset.bp.entities.CompteCourant;
import com.iset.bp.entities.CompteEpargne;
import com.iset.bp.entities.Operation;
import com.iset.bp.entities.Retrait;
import com.iset.bp.entities.Versement;
import com.iset.bp.metier.IBanqueMetier;

@SpringBootApplication
public class MvcBanqueApplication implements CommandLineRunner{ 

	@Autowired	
	private IBanqueMetier BqM; 

	
	@Autowired
	private ClientRepository ClientRep;
	
	@Autowired
	private CompteRepository CompteRep;
	
	@Autowired
	private OperationRepository OperationRep;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MvcBanqueApplication.class, args);
	}
	

   public void run(String... args) throws Exception {
		// TODO Auto-generated method stub		
		Client clt1 = new Client("Amir");
		ClientRep.save(clt1);
		Client clt2 = new Client("Marwa");
		ClientRep.save(clt2);
		ClientRep.save(new Client("Aws"));
		
		
	   Compte cpt1 = new CompteCourant("cpt1",new Date(),7000, clt1,6000);
	   CompteRep.save(cpt1);
       Compte cpt2 = new CompteEpargne("cpt2",new Date(),5000,clt2,5.5);
       CompteRep.save(cpt2);
		
       
     	Operation opr1 = new Versement(new Date(),4000,cpt2);
		OperationRep.save(opr1);
		Operation opr2 = new Retrait(new Date(),5000,cpt2);
		OperationRep.save(opr2);
	    Operation opr3 = new Versement(new Date(),7000,cpt2);
        OperationRep.save(opr3);
	
		
		Operation opr4 = new Versement(new Date(),2000,cpt1);
		OperationRep.save(opr4);
		Operation opr5 = new Retrait(new Date(),8000,cpt1);
		OperationRep.save(opr5);
		Operation opr6 = new Retrait(new Date(),4000,cpt1);
		OperationRep.save(opr6);
		

		BqM.retrait(cpt1.getNumcompte(), 1000);
		BqM.versement(cpt1.getNumcompte(), 2000);
		BqM.virement(cpt1.getNumcompte(),cpt2.getNumcompte(),2000);
		BqM.listOperationCompte(cpt1.getNumcompte(), 3, 1);

	    
   }
   
}

