package com.edugroupe.mymovie.web;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.mymovie.metier.Movie;
import com.edugroupe.mymovie.repositories.MovieRepository;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MovieController {

	@Autowired private MovieRepository movieRepository;
	
	
	@GetMapping
	@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
	public Page<Movie> findAll(@PageableDefault() Pageable page) {
		return movieRepository.findAll(page);
	}
	
	
}
