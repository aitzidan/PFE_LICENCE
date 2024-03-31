package com.lus.dawm.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "categorie")

public class Categorie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name = "designation")
	private String designation;
	@Column(name = "description")
	private String description;
	private List<Produit> produits;

	private Categorie parenCategorie;

	private List<Categorie> subCategories;

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Categorie getParenCategorie() {
		return parenCategorie;
	}

	public void setParenCategorie(Categorie parenCategorie) {
		this.parenCategorie = parenCategorie;
	}

	public List<Categorie> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Categorie> subCategories) {
		this.subCategories = subCategories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
