package com.edugroupe.nanomaniaform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.edugroupe.nanomaniaform.metier.Genre;

@RepositoryRestResource
public interface GenreRepository extends PagingAndSortingRepository<Genre, Integer> {

}
