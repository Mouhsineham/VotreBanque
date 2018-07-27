package org.sid.entities;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mysql.fabric.xmlrpc.base.Data;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",discriminatorType=DiscriminatorType.STRING)
public abstract class Compte implements Serializable {
	@Id
	private String codeCompte;
	private Date dateCreation;
	private double solde;
	@Column(name = "TYPE_CPTE", insertable = false, updatable = false)
	private String type;
	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	private Client client;
	@OneToMany(mappedBy="compte")
	private Collection<Operation> operations;
	public Compte() {
		super();
	}
	public Compte(String codeCompte, Date dateCreation, double solde, Client client) {
		super();
		this.codeCompte =codeCompte;
		this.dateCreation =dateCreation;
		this.solde = solde;
		this.client = client;
	}
	public String getCodeCompte() {
		return codeCompte;
	}
	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Compte [codeCompte=" + codeCompte + ", dateCreation=" + dateCreation + ", solde=" + solde + ", type="
				+ type + ", client=" + client + ", operations=" + operations + "]";
	}
	
	
	
}
