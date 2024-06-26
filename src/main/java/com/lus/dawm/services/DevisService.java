package com.lus.dawm.services;

import com.lus.dawm.model.Client;
import com.lus.dawm.model.DetailDevis;
import com.lus.dawm.model.Devis;
import com.lus.dawm.repository.ClientRepository;
import com.lus.dawm.repository.DetailDevisRepository;
import com.lus.dawm.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevisService {
    @Autowired
    private DevisRepository repository;
    @Autowired
    private DetailDevisRepository repositoryDetails;
    public Devis addDevis(Devis devis) {
        repository.save(devis);
        return devis;
    }
    public DetailDevis addDetailDevis(DetailDevis detailDevis) {
        repositoryDetails.save(detailDevis);
        return detailDevis;
    }

    public List<Devis> getAllDevis() {return repository.findAll();}
    public Optional<Devis> getOneDevis(Long Id) {
        Optional<Devis> c = repository.findById(Id);
        return c;
    }
    public Boolean findOneById(Long id) {
        Devis c = repository.findOneById(id);
        if(c != null){
            return true;
        }
        return false;
    }


    public Devis updateDevis(Long id ,Devis devis) {
        repository.findById(id)
                .map(c -> {
                    c.setObjeDevi(devis.getObjet());
                    c.setIdClient(devis.getIdClient());
                    return repository.save(c);
                })
                .orElseGet(() -> {
                    return null;
                });
        return repository.save(devis);
    }
    public void deleteDevis(Devis devis) {
        repository.delete(devis);
    }

    public Long getMaxId() {
        return repository.findMaxId();
    }


}
