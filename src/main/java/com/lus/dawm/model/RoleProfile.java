package com.lus.dawm.model;

import jakarta.persistence.*;

@Entity
public class RoleProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private ListeRole idRole;
    @ManyToOne
    private Profile idProfile;
    @Column(name = "etat")
    private Integer etat;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Profile getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Profile idProfile) {
        this.idProfile = idProfile;
    }
    public ListeRole getIdRole() {
        return idRole;
    }
    public void setIdRole(ListeRole idRole) {
        this.idRole = idRole;
    }
    public Integer getEtat() {
        return etat;
    }
    public void  setEtat(Integer etat) {
        this.etat = etat;
    }

}
