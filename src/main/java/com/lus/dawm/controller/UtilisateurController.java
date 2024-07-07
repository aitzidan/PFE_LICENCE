
package com.lus.dawm.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.lus.dawm.mapper.UtilisateurDTO;
import com.lus.dawm.model.*;
import com.lus.dawm.repository.UtilisateurRepository;
import com.lus.dawm.security.MyUserDetailsService;
import com.lus.dawm.services.MessageService;
import com.lus.dawm.services.ProductServices;
import com.lus.dawm.services.AuthenticationService;
import com.lus.dawm.services.UtilisateurService;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UtilisateurController {
    private final UtilisateurService userServices;
    private final AuthenticationService authService;

    private  MyUserDetailsService userDetailsService;
    private final ProductServices productServices;
    private final UtilisateurRepository repository;
    private MessageService MessageService;


    @Autowired
    public UtilisateurController(
            UtilisateurService userServices,
            UtilisateurRepository repository,
            MyUserDetailsService userDetailsService,
            AuthenticationService authService,
            ProductServices productServices,
            MessageService MessageService)
    {
        this.authService = authService;
        this.userServices = userServices;
        this.productServices = productServices;
        this.repository = repository;
        this.userDetailsService = userDetailsService;
        this.MessageService = MessageService;
    }


    public record Greeting(List<Utilisateur> liste) { }

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<Utilisateur>> listUsers() {
        List<Utilisateur> utilisateursList = userServices.getListeUsers();
        return ResponseEntity.ok(utilisateursList);
    }
    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> addUsers(
            @ModelAttribute Utilisateur newUser ,
            @RequestParam("idProfile") Long idProfile,
            @RequestParam("image") MultipartFile image) {

        List<Utilisateur> findUserByEmail =  userServices.getUserByEmail(newUser.getEmail());
        Utilisateur findUserByUsername =  userServices.getUserByUsername(newUser.getUsername());

        if(!findUserByEmail.isEmpty() || (findUserByUsername != null) ){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }else{
            newUser.setPassword(userServices.hashedPwd(newUser.getPassword()));

            Profile findProfile =  userServices.getOneProfile(idProfile);
            newUser.setIdProfile(findProfile);

            // Handle file upload
            if (image != null && !image.isEmpty()) {
                try {
                    // Define the path to save the image
                    String directory = "src/main/resources/static/images";
                    String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                    Path path = Paths.get(directory, fileName);

                    // Create the directory if it does not exist
                    Files.createDirectories(path.getParent());

                    // Save the file to the directory
                    Files.write(path, image.getBytes());

                    // Set the image URL in the user
                    newUser.setUrlImage("/images/" + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            Utilisateur user = userServices.addUser(newUser);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }
    }

    @PostMapping("/authentification/")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Utilisateur user) {
        return ResponseEntity.ok(authService.authenticate(user));
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
//    @PutMapping("/utilisateurs/{id}")
//    public Utilisateur updateUser(@RequestBody Utilisateur newUser, @PathVariable Long id) {
//        return userServices.getOneUser(id)
//                .map(user -> {
//                    user.setNom(newUser.getNom());
//                    user.setPrenom(newUser.getPrenom());
//                    user.setUsername(newUser.getUsername());
//                    user.setPassword(newUser.getPassword());
//                    user.setEmail(newUser.getEmail());
//                    return repository.save(user);
//                })
//                .orElseGet(() -> {
//                    return null;
//                });
//    }
    @PutMapping("/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> updateUser(
            @PathVariable int id,
            @ModelAttribute Utilisateur updatedUser,
            @RequestParam("idProfile") Long idProfile,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        Utilisateur existingUser = userServices.getOneUser(id);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update user fields
        existingUser.setNom(updatedUser.getNom());
        existingUser.setPrenom(updatedUser.getPrenom());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAdresse(updatedUser.getAdresse());
        existingUser.setRole(updatedUser.getRole());

        // Update profile
        Profile findProfile = userServices.getOneProfile(idProfile);
        existingUser.setIdProfile(findProfile);

        // Update password if provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(userServices.hashedPwd(updatedUser.getPassword()));
        }

        // Handle file upload if a new image is provided
        if (image != null && !image.isEmpty()) {
            try {
                // Define the path to save the image
                String directory = "src/main/resources/static/images";
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(directory, fileName);

                // Create the directory if it does not exist
                Files.createDirectories(path.getParent());

                // Save the file to the directory
                Files.write(path, image.getBytes());

                // Set the image URL in the user
                existingUser.setUrlImage("/images/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        Utilisateur user = repository.save(existingUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
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
    //TODO:ProfileMethos
    @PostMapping("/profile")
    public ResponseEntity addProfile(@RequestBody JsonNode data) {
        String codeMessage = "ERROR";
        try {
            /*if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                String combinedErrorMessage = String.join(", ", errorMessages);
                StatusMessage errorStatusMessage = new StatusMessage("DATA_ERROR", combinedErrorMessage);
                return new ResponseEntity<>(errorStatusMessage, HttpStatus.BAD_REQUEST);
            }*/
            JsonNode profile = data.get("profile");
            String profileName = profile.get("nom").asText();
            JsonNode rolesList = data.get("role");

            if(this.userServices.loadProfileByName(profileName) == null){
                if(!rolesList.isEmpty()){
                    Profile profileEntity =  this.userServices.saveProfile(profileName);
                    //this is listeRole
                    List<ListeRole> listeRoles = this.userServices.getListeRole();
                    for (ListeRole role : listeRoles) {
                        Integer roleId = role.getId();
                        Long idR = Long.valueOf(roleId);
                        ListeRole listeRoleOpt = this.userServices.findRole(idR);
                        Integer etat = 0;
                        for (JsonNode i : rolesList){
                            if(i.asInt() == roleId){ // Use asInt() to get the integer value from JsonNode
                                etat = 1;
                                break;
                            }
                        }
                        this.userServices.saveRoleProfile(profileEntity , listeRoleOpt , etat);
                        codeMessage = "OK";
                    }
                }else{
                    codeMessage = "EMPTY_DATA";
                }
            }else{
                codeMessage="EXIST_DEJA";
            }
            return new ResponseEntity<>(MessageService.message(codeMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            codeMessage = "ERROR";
            e.printStackTrace();
            return new ResponseEntity<>(MessageService.message(codeMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/profile")
    public ResponseEntity updateProfile(@RequestBody JsonNode data) {
        String codeMessage = "ERROR";
        try {
            /*if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                String combinedErrorMessage = String.join(", ", errorMessages);
                StatusMessage errorStatusMessage = new StatusMessage("DATA_ERROR", combinedErrorMessage);
                return new ResponseEntity<>(errorStatusMessage, HttpStatus.BAD_REQUEST);
            }*/
            JsonNode profile = data.get("profile");
            String profileName = profile.get("nom").asText();
            JsonNode rolesList = data.get("role");

            if(this.userServices.loadProfileByName(profileName) == null){
                if(!rolesList.isEmpty()){
                    Profile profileEntity =  this.userServices.saveProfile(profileName);
                    //this is listeRole
                    List<ListeRole> listeRoles = this.userServices.getListeRole();
                    for (ListeRole role : listeRoles) {
                        Integer roleId = role.getId();
                        Long idR = Long.valueOf(roleId);
                        ListeRole listeRoleOpt = this.userServices.findRole(idR);
                        Integer etat = 0;
                        for (JsonNode i : rolesList){
                            if(i.asInt() == roleId){ // Use asInt() to get the integer value from JsonNode
                                etat = 1;
                                break;
                            }
                        }
                        this.userServices.saveRoleProfile(profileEntity , listeRoleOpt , etat);
                        codeMessage = "OK";
                    }
                }else{
                    codeMessage = "EMPTY_DATA";
                }
            }else{
                codeMessage="EXIST_DEJA";
            }
            return new ResponseEntity<>(MessageService.message(codeMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            codeMessage = "ERROR";
            e.printStackTrace();
            return new ResponseEntity<>(MessageService.message(codeMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
