package org.sid.metier;


import java.util.Date;

import org.sid.dao.CompteCourantRepository;
import org.sid.dao.CompteEpRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourante;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier{
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private CompteCourantRepository compteCourantRepository;
    @Autowired
    private CompteEpRepository compteEpRepository;
    @Autowired
    private OperationRepository operationRepository; 
	@Override
	public Compte consulterCompte(String codeCpte){
        Compte cp=compteRepository.getOne(codeCpte);
        if(cp==null) throw new RuntimeException("compte introuvable");
        if(cp.getType().equals("CE")){
        	return compteEpRepository.findOne(codeCpte);
        }else{
        	return compteCourantRepository.findOne(codeCpte);
        }
	}
	@Override
	public void verser(String codeCpte, double montant) {
        Compte cp=consulterCompte(codeCpte);
        operationRepository.save(new Versement(new Date(), montant,cp));
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
	    Compte  cp=consulterCompte(codeCpte);
	    double facilitesCaisse=0;
	    if(cp instanceof CompteCourante)
	    	facilitesCaisse=((CompteCourante) cp).getDecouvert();
	    if(cp.getSolde()+facilitesCaisse<montant)
	    	throw new RuntimeException("Solde insuffisant");	    
	    
        Retrait r=new Retrait(new Date(), montant,cp);
        operationRepository.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
      retirer(codeCpte1,montant);
      retirer(codeCpte1,montant);	
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		
	    return   operationRepository.listOperation(codeCpte, new PageRequest(page, size));

	}

}
