package com.lus.dawm.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.DiscriminatorFormula;

import java.io.Serializable;
import java.util.List;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
/*@DiscriminatorFormula("...")
@DiscriminatorValue("not null")*/
@DiscriminatorColumn(name = "dtype")


public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3832626162173359411L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	protected int id;
	protected String nom, prenom, username, pwd;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

    public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
