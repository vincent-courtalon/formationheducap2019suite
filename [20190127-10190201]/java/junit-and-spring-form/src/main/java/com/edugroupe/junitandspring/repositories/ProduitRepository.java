package com.edugroupe.junitandspring.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edugroupe.junitandspring.metier.Produit;

@Service
public class ProduitRepository {

	@Value("${boutique.prix.maximum}")
	private double prixProduitMax;
	
	public Produit getProduit() {
		Produit p = new Produit(1, "miel des carpathes", 12.99, 0.5);
		p.setPrixMaximum(prixProduitMax);
		return p;
	}
	
}
