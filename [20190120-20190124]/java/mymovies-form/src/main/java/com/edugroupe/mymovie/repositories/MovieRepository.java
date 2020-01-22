package com.edugroupe.mymovie.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.edugroupe.mymovie.metier.Movie;

@RepositoryRestResource
public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer> {

}
