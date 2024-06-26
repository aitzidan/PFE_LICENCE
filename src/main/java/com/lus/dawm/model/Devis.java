package com.lus.dawm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    protected LocalDateTime dateCreation;
    @ManyToOne
    private Client idClient;
    protected String reference;
    protected String objet;

    protected Double totalTh;

    protected Double totalTtc;

    protected Double tva;

    protected String etatDevis;

    protected LocalDate dateEcheance;
    @OneToMany(mappedBy = "idDevis")
    private List<DetailDevis> detailDevis;



    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjeDevi(String objet) {
        this.objet = objet;
    }

    public Double getTotalTh() {
        return totalTh;
    }

    public void setTotalTh(Double totalTh) {
        this.totalTh = totalTh;
    }

    public Double getTotalTtc() {
        return totalTtc;
    }

    public void setTotalTtc(Double totalTtc) {
        this.totalTtc = totalTtc;
    }

    public Double getTva() {
        return tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public String getEtatDevis() {
        return etatDevis;
    }

    public void setEtatDevis(String etatDevis) {
        this.etatDevis = etatDevis;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }
    public List<DetailDevis> getDetailDevis() {
        return detailDevis;
    }

    public void setDetailDevis(List<DetailDevis> detailDevis) {
        this.detailDevis = detailDevis;
    }


}
