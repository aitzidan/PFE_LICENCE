package com.lus.dawm.controller;

import java.util.List;

import com.lus.dawm.mapper.UtilisateurDTO;
import com.lus.dawm.model.Utilisateur;
import com.lus.dawm.repository.UtilisateurRepository;
import com.lus.dawm.services.ProductServices;
import com.lus.dawm.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UtilisateurController {
    private final UtilisateurService userServices;

    private final ProductServices productServices;
    private final UtilisateurRepository repository;

    @Autowired
    public UtilisateurController(UtilisateurService userServices ,UtilisateurRepository repository, ProductServices productServices) {
        this.userServices = userServices;
        this.productServices = productServices;
        this.repository = repository;
    }


    public record Greeting(List<Utilisateur> liste) { }

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<UtilisateurDTO>> listUsers() {
        List<UtilisateurDTO> utilisateursList = userServices.getListeUsersAsDTO();
        return ResponseEntity.ok(utilisateursList);
    }
    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> addUsers(@RequestBody Utilisateur newUser) {
        Utilisateur user = userServices.addUser(newUser);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> getOneUser(@PathVariable("id") long id) {
        Utilisateur user = userServices.getOneUser(id).orElseGet(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
    @PutMapping("/utilisateurs/{id}")
    public Utilisateur updateUser(@RequestBody Utilisateur newUser, @PathVariable Long id) {

        return userServices.getOneUser(id)
                .map(user -> {
                    user.setNom(newUser.getNom());
                    user.setPrenom(newUser.getPrenom());
                    user.setUsername(newUser.getUsername());
                    user.setPwd(newUser.getPwd());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    return null;
                });
    }
    @DeleteMapping("/utilisateurs/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Utilisateur deletedUser = userServices.deleteUser(id);
        if (deletedUser != null) {
            return ResponseEntity.ok().build(); // User was found and deleted
        } else {
            return ResponseEntity.notFound().build(); // User was not found
        }
    }
}
