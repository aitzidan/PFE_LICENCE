package com.lus.dawm.eshop;

import com.lus.dawm.controller.Home;
import com.lus.dawm.controller.ProductController;
import com.lus.dawm.repository.ProductRepository;
import com.lus.dawm.services.ProductServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses= Home.class)
@ComponentScan(basePackageClasses= ProductController.class)
@ComponentScan(basePackageClasses= ProductRepository.class)
@ComponentScan(basePackageClasses= ProductServices.class)
@EnableJpaRepositories(basePackages = {"com.lus.dawm.repository"})
@EntityScan(basePackages = {"com.lus.dawm.model"})


public class EshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
		System.out.println("Test my app !");
	}
}
