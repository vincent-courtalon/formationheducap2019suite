package com.edugroupe.springboutique.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springboutique.metier.Produit;

public interface ProduitRepository extends PagingAndSortingRepository<Produit, Integer> {
	
}
