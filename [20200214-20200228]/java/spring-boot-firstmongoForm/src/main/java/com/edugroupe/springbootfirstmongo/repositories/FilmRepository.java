package com.edugroupe.springbootfirstmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edugroupe.springbootfirstmongo.metier.Film;

public interface FilmRepository extends MongoRepository<Film, String> {

	List<Film> findByAnneeGreaterThan(int annee);
}
