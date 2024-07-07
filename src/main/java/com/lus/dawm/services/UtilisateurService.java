package com.lus.dawm.services;

import com.lus.dawm.mapper.UtilisateurDTO;
import com.lus.dawm.model.ListeRole;
import com.lus.dawm.model.Profile;
import com.lus.dawm.model.RoleProfile;
import com.lus.dawm.model.Utilisateur;
import com.lus.dawm.repository.ListeRoleRepository;
import com.lus.dawm.repository.ProfileRepository;
import com.lus.dawm.repository.RoleProfileRepository;
import com.lus.dawm.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    private ProfileRepository profileRepository;
    private RoleProfileRepository roleProfileRepository;

    private ListeRoleRepository listeRoleRepository;
    @Autowired
    public void setUserRepository(UtilisateurRepository utilisateurRepository  ,
                                  ProfileRepository profileRepository,
                                  RoleProfileRepository roleProfileRepository,
                                  ListeRoleRepository listeRoleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.profileRepository = profileRepository;
        this.roleProfileRepository = roleProfileRepository;
        this.listeRoleRepository = listeRoleRepository;
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
        utilisateurDTO.setPassword(utilisateur.getPassword());
        return utilisateurDTO;
    }

    public List<Utilisateur> getUserByEmail(String email) {
        List<Utilisateur> utilisateurs = (List<Utilisateur>) utilisateurRepository.findByEmail(email);
        return (List<Utilisateur>) utilisateurs;
    }
    public Utilisateur getUserByUsername(String username) {
        Utilisateur utilisateurs = (Utilisateur) utilisateurRepository.findByUsername(username);
        return (Utilisateur) utilisateurs;
    }

    public Optional<Utilisateur> getOneUser(Long id) {
        return utilisateurRepository.findById(id);
    }
    public Utilisateur updateUser(Utilisateur user) {
        return utilisateurRepository.save(user);
    }

    public Utilisateur findByUsername(String username ){
        return  utilisateurRepository.findByUsername(username);
    }
    public String hashedPwd(String password ){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String Pwd = passwordEncoder.encode(password);
        return Pwd;
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user= utilisateurRepository.findByUsername(username);
        if(user == null){
            return (UserDetails) new UsernameNotFoundException("User not found");
        }
        return (UserDetails) user;
    }
    public Profile saveProfile(String nom) throws UsernameNotFoundException {
        Profile profile = new Profile();
        profile.setNom(nom);
        profile.setDateCreation(LocalDateTime.now());
        return profileRepository.save(profile);
    }
    public Profile loadProfileByName(String name) throws UsernameNotFoundException {
        Profile profile= profileRepository.findOneByNom(name);
        return profile;
    }
    public ListeRole findRole(Long id){
        ListeRole findRole = this.listeRoleRepository.findOneById(id);
        return findRole;
    }
    public RoleProfile saveRoleProfile(Profile profile , ListeRole Role , Integer etat) throws UsernameNotFoundException {
        RoleProfile r = new RoleProfile();
        r.setIdProfile(profile);
        r.setIdRole(Role);
        r.setEtat(etat);
        return this.roleProfileRepository.save(r);
    }

    public List<ListeRole> getListeRole(){
        return this.listeRoleRepository.findAll(); // This will return a List<ListeRole>
    }
    public Profile getOneProfile(Long id) {
        return profileRepository.findOneById(id);
    }

}
