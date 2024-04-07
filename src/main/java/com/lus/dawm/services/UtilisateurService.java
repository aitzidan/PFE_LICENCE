package com.lus.dawm.services;

import com.lus.dawm.mapper.UtilisateurDTO;
import com.lus.dawm.model.Utilisateur;
import com.lus.dawm.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public void setUserRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    public List<Utilisateur> getListeUsers() {
        return utilisateurRepository.findAll();
    }
    public Utilisateur addUser(Utilisateur user) {
        utilisateurRepository.save(user);
        return user;
    }
    public Utilisateur getOneUser(int id) {
        return utilisateurRepository.findById((long) id).orElse(null);
    }
    public Utilisateur deleteUser(int id) {
        Optional<Utilisateur> userOptional = utilisateurRepository.findById((long) id);

        if (userOptional.isPresent()) {
            Utilisateur user = userOptional.get();
            utilisateurRepository.delete(user);
            return user;
        } else {
            return null; // or throw an exception indicating that the user with the given ID was not found
        }
    }

    public List<UtilisateurDTO> getListeUsersAsDTO() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UtilisateurDTO convertToDTO(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setUsername(utilisateur.getUsername());
        utilisateurDTO.setPwd(utilisateur.getPwd());
        return utilisateurDTO;
    }
    public Optional<Utilisateur> getOneUser(Long id) {
        return utilisateurRepository.findById(id);
    }
    public Utilisateur updateUser(Utilisateur user) {
        return utilisateurRepository.save(user);
    }


}
