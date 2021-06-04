package com.iset.bp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iset.bp.entities.Compte;
import com.iset.bp.entities.Operation;
import com.iset.bp.metier.IBanqueMetier;

@Controller
public class BanqueController {

	@Autowired
	private IBanqueMetier iBanqueMetier;

   @RequestMapping("/comptes")
   public String index() {
	  return "comptes"; 
    }
  
   @RequestMapping("/consultercompte")
   public String consulterCompte(Model model, String numcompte,
		   @RequestParam(name="page",defaultValue="0")int page,
           @RequestParam(name="size",defaultValue="5")int size) {

	   //numcompte = "cpt1";
	   
    model.addAttribute("numcompte",numcompte);
    try {
	Compte cpt =iBanqueMetier.getCompte(numcompte); 
    
	model.addAttribute("compte",cpt);

   Page<Operation> pageOperations = iBanqueMetier.listOperationCompte(numcompte,page,size);
   model.addAttribute("listOperations",pageOperations.getContent());
   int[] pages=new int[pageOperations.getTotalPages()];
   
    model.addAttribute("pages", pages);
	
    }catch (Exception e) {
		model.addAttribute("exception",e);
	}
   return "comptes";
   }
   
   
   @RequestMapping(value="/saveOperation",method=RequestMethod.POST)
   public String saveOperation(Model model, String Operation, String numcompte,
                               double montant, String numcompte2) {
	  try {
			if(Operation.equals("versement")) {
				
				iBanqueMetier.versement(numcompte,montant);
				
			} else if(Operation.equals("retrait")) {
				
				iBanqueMetier.retrait(numcompte,montant);
				
			} else  {
				
				iBanqueMetier.virement(numcompte,numcompte2,montant);
				
			} 
			
		}catch (Exception e) {
			
			model.addAttribute("error",e);
			return "redirect:/consultercompte?numcompte="+numcompte+"&error="+e.getMessage();  
		
		}
		 
            return "redirect:/consultercompte?numcompte="+numcompte;
   
   }

} 
