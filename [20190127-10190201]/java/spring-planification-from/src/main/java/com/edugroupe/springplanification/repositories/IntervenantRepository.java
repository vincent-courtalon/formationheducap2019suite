package com.edugroupe.springplanification.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springplanification.metier.Intervenant;

public interface IntervenantRepository extends PagingAndSortingRepository<Intervenant, Integer> {

}
