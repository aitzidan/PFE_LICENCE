package com.lus.dawm.services;
import com.lus.dawm.model.Utilisateur;
import com.lus.dawm.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UtilisateurRepository repository;

    public UserDetailsServiceImp(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user= repository.findByUsername(username);
        if(user == null){
            return (UserDetails) new UsernameNotFoundException("User not found");
        }
        return (UserDetails) user;
    }
}
