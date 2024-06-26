package com.lus.dawm.controller;

import com.lus.dawm.model.Client;
import com.lus.dawm.model.DetailDevis;
import com.lus.dawm.model.Devis;
import com.lus.dawm.model.StatusMessage;
import com.lus.dawm.services.ClientService;
import com.lus.dawm.services.DevisService;
import com.lus.dawm.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DevisController {
    @Autowired
    private final DevisService DevisService;
    @Autowired
    private final ClientService ClientService;

    private  MessageService MessageService;

    public  DevisController(DevisService DevisService ,ClientService ClientService , MessageService MessageService){
        this.DevisService = DevisService;
        this.MessageService = MessageService;
        this.ClientService = ClientService;
    }

    @PostMapping(value = "/devis", consumes = "application/json", produces = "application/json")
    public ResponseEntity addDevis(@Valid @RequestBody Devis devis, BindingResult bindingResult) {
        String codeMessage = "ERROR";
        try {
            // Validation
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                String combinedErrorMessage = String.join(", ", errorMessages);
                StatusMessage errorStatusMessage = new StatusMessage("DATA_ERROR", combinedErrorMessage);
                return new ResponseEntity<>(errorStatusMessage, HttpStatus.BAD_REQUEST);
            }

            Long clientId = Long.valueOf(devis.getIdClient().getId());
            Optional<Client> c = ClientService.getOneClient(clientId);
            if (c.isPresent()) {
                codeMessage = "OK";
                Long maxId = DevisService.getMaxId() + 1;
                String reference = "DEVIS-" + maxId;
                devis.setReference(reference);
                Devis savedDevis = DevisService.addDevis(devis);
                for (DetailDevis detail : devis.getDetailDevis()) {
                    detail.setIdDevis(savedDevis);
                    System.out.println(detail.getDesignation());
                    DetailDevis savedDetail = DevisService.addDetailDevis(detail);
                }
                return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.CREATED);
            } else {
                codeMessage = "NOT_EXIST";
                return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            codeMessage = "ERROR";
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/devis")
    public ResponseEntity<List<Devis>> getClients() {
        List<Devis> listeDevis = DevisService.getAllDevis();
        return ResponseEntity.ok(listeDevis);
    }

    @PutMapping("/devis")
    public ResponseEntity updateDevis(@RequestBody Devis devis , BindingResult bindingResult) {

        String codeMessage = "ERROR";
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                String combinedErrorMessage = String.join(", ", errorMessages);
                StatusMessage errorStatusMessage = new StatusMessage("DATA_ERROR", combinedErrorMessage);
                return new ResponseEntity<>(errorStatusMessage, HttpStatus.BAD_REQUEST);
            }
            Long devisId = Long.valueOf(devis.getId());
            Boolean isExist = DevisService.findOneById(devisId);
            if(isExist){
                    codeMessage = "OK";
                    Devis d = DevisService.updateDevis(devisId,devis);
                    return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.OK);
            }else{
                codeMessage = "NOT_EXIST";
                return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            codeMessage = "ERROR";
            e.printStackTrace();
            return new ResponseEntity<>(MessageService.message(codeMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/devis")
    public ResponseEntity deleteClient(@RequestBody Devis devis ) {

        String codeMessage = "ERROR";
        try {
            Long clientId = Long.valueOf(devis.getId());
            Boolean isExist = DevisService.findOneById(clientId);
            if(isExist){
                codeMessage = "OK";
                DevisService.deleteDevis(devis);
                return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.OK);
            }else{
                codeMessage = "NOT_EXIST";
                return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            codeMessage = "ERROR";
            e.printStackTrace();
            return new ResponseEntity<>(MessageService.message(codeMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getDetailsDevis")
    public ResponseEntity getOneClient(@RequestBody Devis devis ) {

        String codeMessage = "ERROR";
        try {
            Long devisId = Long.valueOf(devis.getId());
            Boolean isExist = DevisService.findOneById(devisId);
            if(isExist){
                codeMessage = "OK";
                Optional<Devis> d = DevisService.getOneDevis(devisId);
                return new ResponseEntity<>(d , HttpStatus.OK);
            }else{
                codeMessage = "NOT_EXIST";
                return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            codeMessage = "ERROR";
            e.printStackTrace();
            return new ResponseEntity<>(MessageService.message(codeMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
