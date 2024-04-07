package com.lus.dawm.repository;

import com.lus.dawm.model.Produit;
import com.lus.dawm.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}

