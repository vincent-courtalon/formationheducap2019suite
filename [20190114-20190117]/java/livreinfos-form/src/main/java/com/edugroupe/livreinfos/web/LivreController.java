package com.edugroupe.livreinfos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.livreinfos.metier.Livre;
import com.edugroupe.livreinfos.repositories.LivreRepository;

import lombok.Getter;

@RestController
@RequestMapping("/livres")
@CrossOrigin
public class LivreController {

	@Autowired private LivreRepository livreRepository;
	
	@GetMapping
	public Iterable<Livre> liste() {
		return livreRepository.findAll();
	}
	
	@GetMapping("/{isbn:[0-9]+}")
	public ResponseEntity<Livre> findByIsbn(@PathVariable("isbn") String isbn) {
		return livreRepository.findByIsbn(isbn)
								.map(l -> new ResponseEntity<>(l, HttpStatus.OK))
								.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	
}
