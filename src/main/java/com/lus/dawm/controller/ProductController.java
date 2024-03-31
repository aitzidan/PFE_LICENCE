package com.lus.dawm.controller;

import com.lus.dawm.model.Produit;
import com.lus.dawm.repository.ProductRepository;
import com.lus.dawm.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/admin/product/")

public class ProductController {
    private final ProductServices productServices;

    @Autowired
    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("create")
    public String create(Model model){
        model.addAttribute("product", new Produit());
        return "admin/product/create";
    }
    @PostMapping("add")
    public String add(@ModelAttribute Produit product){
        productServices.addProduct(product);
        return "redirect:create";
    }
    @GetMapping("list")
    public String list(Model model){
        List<Produit> produitList = productServices.getListeProduct();
        model.addAttribute("produits", produitList);
        return "admin/product/listProduct";
    }

}
