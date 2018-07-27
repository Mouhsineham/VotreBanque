package org.sid.entities;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
 
@Entity
@DiscriminatorValue("CC")
public class CompteCourante extends Compte{
	private double decouvert;
	public CompteCourante() {
		super();
	}
	public CompteCourante(String codeCompte, Date dateCreation, double solde, Client client, double decouvert) {
		super(codeCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	public double getDecouvert(){
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
}
