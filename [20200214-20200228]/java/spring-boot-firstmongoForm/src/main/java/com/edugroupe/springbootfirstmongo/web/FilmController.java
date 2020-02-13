package com.edugroupe.springbootfirstmongo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.springbootfirstmongo.metier.Film;
import com.edugroupe.springbootfirstmongo.repositories.FilmRepository;

@RestController
@RequestMapping("/films")
public class FilmController {

	@Autowired private FilmRepository filmRepository;
	
	@GetMapping
	public List<Film> findAll() {
		return this.filmRepository.findAll();
	}
	
	@GetMapping("/annee/after/{annee:[0-9]+}")
	public List<Film> findByAnneeAfter(@PathVariable("annee") int annee) {
		return this.filmRepository.findByAnneeGreaterThan(annee);
	}
	
	@PostMapping
	public ResponseEntity<Film> createFilm(@RequestBody Film film) {
	
		if (film.getId() != null && film.getId().length() > 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Film>(filmRepository.insert(film), HttpStatus.CREATED);
	}
	
	
	
	
}
