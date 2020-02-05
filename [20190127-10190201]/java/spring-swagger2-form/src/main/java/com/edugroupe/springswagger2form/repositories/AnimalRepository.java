package com.edugroupe.springswagger2form.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springswagger2form.metier.Animal;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Integer> {

}
