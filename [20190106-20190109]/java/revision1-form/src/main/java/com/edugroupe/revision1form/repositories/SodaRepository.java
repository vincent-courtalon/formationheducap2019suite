package com.edugroupe.revision1form.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.edugroupe.revision1form.metier.Soda;

@RepositoryRestResource
public interface SodaRepository extends PagingAndSortingRepository<Soda, Integer> {

}
