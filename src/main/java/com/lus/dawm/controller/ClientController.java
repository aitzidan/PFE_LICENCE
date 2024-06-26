package com.lus.dawm.controller;

import com.lus.dawm.model.Client;
import com.lus.dawm.model.StatusMessage;
import com.lus.dawm.services.ClientService;
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
public class ClientController {
    @Autowired
    private final ClientService ClientService;

    private  MessageService MessageService;

    public  ClientController(ClientService ClientService , MessageService MessageService){
        this.ClientService = ClientService;
        this.MessageService = MessageService;
    }

    @PostMapping("/client")
    public ResponseEntity  addClient(@Valid @RequestBody Client client, BindingResult bindingResult) {
        String codeMessage = "ERROR";
        try {
            /* Validation data*/
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                String combinedErrorMessage = String.join(", ", errorMessages);
                StatusMessage errorStatusMessage = new StatusMessage("DATA_ERROR", combinedErrorMessage);
                return new ResponseEntity<>(errorStatusMessage, HttpStatus.BAD_REQUEST);
            }

            Boolean isPresent = ClientService.findOneByIce(client.getIce());
            if (isPresent) {
                codeMessage = "ICE_EXISTE";
                return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.ALREADY_REPORTED);
            }else {
                codeMessage = "OK";
                Client c = ClientService.addClient(client);
                return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.CREATED);
            }

        } catch (Exception e) {
            codeMessage = "ERROR";
            e.printStackTrace();
            return new ResponseEntity<>(MessageService.message(codeMessage),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/client")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> listeClient = ClientService.getAllClient();
        return ResponseEntity.ok(listeClient);
    }
    @PutMapping("/client")
    public ResponseEntity updateClient(@RequestBody Client client , BindingResult bindingResult) {

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
            Long clientId = Long.valueOf(client.getId());
            Boolean isExist = ClientService.findOneById(clientId);
            if(isExist){
                Boolean isPresent = ClientService.findOneByIceUpd(client.getIce() , clientId);
                if (isPresent) {
                    codeMessage = "ICE_EXISTE";
                    return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.ALREADY_REPORTED);
                }else {
                    codeMessage = "OK";
                    Client c = ClientService.updateClient(clientId, client);
                    return new ResponseEntity<>(MessageService.message(codeMessage), HttpStatus.CREATED);
                }
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
    @DeleteMapping("/client")
    public ResponseEntity deleteClient(@RequestBody Client client ) {

        String codeMessage = "ERROR";
        try {
            Long clientId = Long.valueOf(client.getId());
            Boolean isExist = ClientService.findOneById(clientId);
            if(isExist){
                codeMessage = "OK";
                ClientService.deleteClient(client);
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
    @GetMapping("/getOneClient")
    public ResponseEntity getOneClient(@RequestBody Client client ) {

        String codeMessage = "ERROR";
        try {
            Long clientId = Long.valueOf(client.getId());
            Boolean isExist = ClientService.findOneById(clientId);
            if(isExist){
                codeMessage = "OK";
                Optional<Client> c = ClientService.getOneClient(clientId);
                return new ResponseEntity<>(c , HttpStatus.OK);
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
