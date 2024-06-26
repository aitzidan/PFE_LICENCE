package com.lus.dawm.repository;

import com.lus.dawm.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByEmail(String email);
    Utilisateur findByUsername(String username);

    //Optional<Utilisateur> findByUsername(String username);


    Utilisateur findByEmailAndPwd(String email , String pwd);
}


