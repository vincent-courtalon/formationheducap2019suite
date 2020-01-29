package com.edugroupe.junitandspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.junitandspring.metier.Produit;
import com.edugroupe.junitandspring.repositories.ProduitRepository;

@RestController
@RequestMapping("/produits")
public class ProduitController {

	@Autowired private ProduitRepository produitRepository;
	
	@GetMapping
	public Produit home() {
		return produitRepository.getProduit();
	}
	
}
