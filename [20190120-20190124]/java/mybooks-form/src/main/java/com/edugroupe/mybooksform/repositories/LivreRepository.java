package com.edugroupe.mybooksform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.edugroupe.mybooksform.metier.Livre;

@RepositoryRestResource
public interface LivreRepository extends PagingAndSortingRepository<Livre, Integer> {

}
