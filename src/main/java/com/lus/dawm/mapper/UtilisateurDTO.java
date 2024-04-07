package com.lus.dawm.mapper;

import java.io.Serializable;

public class UtilisateurDTO  implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String pwd;

    // Constructors
    public UtilisateurDTO() {}

    public UtilisateurDTO(int id, String nom, String prenom, String username , String pwd) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.pwd = pwd;
    }

    // Getters and setters
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
