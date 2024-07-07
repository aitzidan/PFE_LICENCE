package com.lus.dawm.eshop;

import com.lus.dawm.configuration.FileUploadConfig;
import com.lus.dawm.controller.ClientController;
import com.lus.dawm.controller.Home;
import com.lus.dawm.controller.UtilisateurController;
import com.lus.dawm.repository.ProductRepository;
import com.lus.dawm.repository.UtilisateurRepository;
import com.lus.dawm.security.SecurityConfig;
import com.lus.dawm.security.SpringFoxConfig;
import com.lus.dawm.services.ProductServices;
import com.lus.dawm.services.UtilisateurService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses= Home.class)
@ComponentScan(basePackageClasses= ProductRepository.class)
@ComponentScan(basePackageClasses= ProductServices.class)
@ComponentScan(basePackageClasses= UtilisateurController.class)
@ComponentScan(basePackageClasses= ClientController.class)
@ComponentScan(basePackageClasses= UtilisateurRepository.class)
@ComponentScan(basePackageClasses= UtilisateurService.class)
@ComponentScan(basePackageClasses= SecurityConfig.class)
@ComponentScan(basePackageClasses= SpringFoxConfig.class)
@ComponentScan(basePackageClasses= FileUploadConfig.class)

@EnableJpaRepositories(basePackages = {"com.lus.dawm.repository"})
@EntityScan(basePackages = {"com.lus.dawm.model"})
@ComponentScan(basePackages = {"com.lus.dawm.services"})
@ComponentScan(basePackages = {"com.lus.dawm.security"})
@ComponentScan(basePackages = {"com.lus.dawm.filter"})
@ComponentScan(basePackages = {"com.lus.dawm.controller"})


public class EshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
		System.out.println("Test my app !");
	}
}
