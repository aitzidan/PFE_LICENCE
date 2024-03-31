package com.lus.dawm.model;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Panier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dateCreation;

	private Client client;

	private List<LigneCommade> ligneCommades;

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneCommade> getLigneCommades() {
		return ligneCommades;
	}

	public void setLigneCommades(List<LigneCommade> ligneCommades) {
		this.ligneCommades = ligneCommades;
	}

}
