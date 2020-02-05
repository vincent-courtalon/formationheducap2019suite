package com.edugroupe.springswagger2form.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springswagger2form.metier.Animal;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Integer> {

	List<Animal> findByZooId(int id);
	
}
