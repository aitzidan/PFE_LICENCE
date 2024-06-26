package com.lus.dawm.security;

import com.lus.dawm.model.Utilisateur;
import com.lus.dawm.repository.UtilisateurRepository;
import com.lus.dawm.services.ProductServices;
import com.lus.dawm.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService {
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public MyUserDetailsService(
            UtilisateurRepository utilisateurRepository
            )
    {
        this.utilisateurRepository = utilisateurRepository;
    }
    public String hashedPwd(String password ){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String Pwd = passwordEncoder.encode(password);
        return Pwd;
    }
    public Utilisateur UserDetailsService(String email , String pwd ){

        return (Utilisateur) this.utilisateurRepository.findByEmailAndPwd(email , pwd);
    }
}
