package com.lus.dawm.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
@Table(name = "commande")
@Entity
public class Commande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private Date dateCreation, dateLivraison;
	private String adresseLivraison;

	private Client client;

	private List<LigneCommade> ligneCommades;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
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
