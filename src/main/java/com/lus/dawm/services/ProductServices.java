package com.lus.dawm.services;

import com.lus.dawm.model.Produit;
import com.lus.dawm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    private ProductRepository produitRepository;
    @Autowired
    public void setProduitRepository(ProductRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
    public void addProduct(Produit product) {
        produitRepository.save(product);
    }
    public void getProduct(String designation) {
        produitRepository.findByDesignation(designation);
    }
    public List<Produit> getListeProduct() {
        return produitRepository.findAll();
    }
}