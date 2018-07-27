package org.sid;
import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourante;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VotreBanqueApplication implements CommandLineRunner {
    @Autowired
	private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private IBanqueMetier banqueMetier;
    
	public static void main(String[] args) {
		
	        SpringApplication.run(VotreBanqueApplication.class, args);
	        
	}
	        
	@Override
	public void run(String... args) throws Exception {
		Client    C1=clientRepository.save(new Client("Mouhsine", "H@gmail.com"));
		Client    C2=clientRepository.save(new Client("Hamza","A@gmail.com"));
		
		Compte    CP1=compteRepository.save(new CompteCourante("d1", new Date(),1590,C1, 4.5));
		Compte    CP2=compteRepository.save(new CompteEpargne("d2", new Date(), 1527,C2, 5.5));
		Compte    CP3=compteRepository.save(new CompteEpargne("d3", new Date(), 1593, C1, 5.5));
				  
		
		operationRepository.save(new Versement(new Date(), 500, CP1));
		operationRepository.save(new Versement(new Date(), 500, CP1));
		operationRepository.save(new Versement(new Date(), 500, CP1));
		operationRepository.save(new Retrait(new Date(), 400, CP1));
		
		operationRepository.save(new Versement(new Date(), 500, CP2));
		operationRepository.save(new Versement(new Date(), 500, CP2));
		operationRepository.save(new Versement(new Date(), 500, CP2));
		operationRepository.save(new Retrait(new Date(), 400, CP2));
		//banqueMetier.verser("d1",1111);
		//banqueMetier.verser("d2",2222);
		

	}
}
