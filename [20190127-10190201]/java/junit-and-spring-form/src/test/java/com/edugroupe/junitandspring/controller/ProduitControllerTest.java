package com.edugroupe.junitandspring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.edugroupe.junitandspring.metier.Produit;
import com.edugroupe.junitandspring.repositories.ProduitRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProduitControllerTest {
	
	@Autowired private ProduitController produitController;
	
	@MockBean private ProduitRepository produitRepository;
	/*
	 * Mockito vous permet de simuler des dependances dans vos test unitaire
	 * par exemple ici, je peux injecter un "faux" repository, qui repondera comme je
	 * le désire, dans le controller à tester 
	 * 
	 * comme je controlle exactement ce que renvoie le repository
	 * je ne test effectivement que le controller
	 * 
	 */
	
	
	
	@Test
	@DisplayName("test direct appel méthode produitController")
	public void testGetProduit() {
		// si le controller rapelle getProduit dans produitRepository
		// alors renvoyer le produit indiqué
		when(produitRepository.getProduit())
							 .thenReturn(new Produit(1, "steak d'autruche", 25.99, 2.0));
							  
		
		final Produit p = produitController.home();
		assertAll(
				() -> assertEquals(1, p.getId()),
				() -> assertEquals("steak d'autruche", p.getNom()),
				() -> assertEquals(25.99, p.getPrix()),
				() -> assertEquals(2.0, p.getPoids())
				);
		
		verify(produitRepository, times(1)).getProduit();
	}

}
