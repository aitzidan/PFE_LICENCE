package com.lus.dawm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "detail_devis")
public class DetailDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @ManyToOne
    private Devis idDevis;
    @NotBlank(message = "Le designation est obligatoire")
    protected String designation;
    protected Double prixU;
    protected String unite;
    protected int qte;
    protected Double montantHT;
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Devis getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(Devis idDevis) {
        this.idDevis = idDevis;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrixU() {
        return prixU;
    }

    public void setPrixU(Double prixU) {
        this.prixU = prixU;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Double getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(Double montantHT) {
        this.montantHT = montantHT;
    }
}
