package com.lus.dawm.services;

import com.lus.dawm.model.Client;
import com.lus.dawm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    public Client addClient(Client client) {
        repository.save(client);
        return client;
    }
    public List<Client> getAllClient() {return repository.findAll();}
    public Optional<Client> getOneClient(Long Id) {
        Optional<Client> c = repository.findById(Id);
        return c;
    }
    public Boolean findOneByIce(String ice) {
        if(repository.findOneByIce(ice) != null){
            return true;
        }
        return false;
    }
    public Boolean findOneByIceUpd(String ice , Long id) {
        Client c = repository.findOneByIce(ice);
        if(repository.findOneByIce(ice) != null && c.getId() != id){
            return true;
        }
        return false;
    }
    public Boolean findOneById(Long id) {
        Client c = repository.findOneById(id);
        if(c != null){
            return true;
        }
        return false;
    }


    public Client updateClient(Long id ,Client client) {
         repository.findById(id)
                .map(c -> {
                    c.setNom(client.getNom());
                    c.setPrenom(client.getPrenom());
                    c.setNom(client.getNom());
                    c.setEmail(client.getEmail());
                    c.setFax(client.getFax());
                    c.setIce(client.getIce());
                    c.setTelephone(client.getTelephone());
                    c.setVille(client.getVille());
                    c.setRc(client.getRc());
                    return repository.save(c);
                })
                .orElseGet(() -> {
                    return null;
                });
         return repository.save(client);
    }
    public void deleteClient(Client client) {
        repository.delete(client);
    }

}
