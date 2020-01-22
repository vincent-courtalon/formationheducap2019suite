package com.edugroupe.mybooksform.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.mybooksform.metier.Lecteur;
import com.edugroupe.mybooksform.metier.Livre;
import com.edugroupe.mybooksform.repositories.LecteurRepository;
import com.edugroupe.mybooksform.repositories.LivreRepository;

@RestController
@RequestMapping("/lecteurs")
@CrossOrigin
public class LecteurController {

	@Autowired private LecteurRepository lecteurRepository;
	@Autowired private LivreRepository livreRepository;
	
	
	@GetMapping("/livres")
	@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
	public Iterable<Livre> listeLivres() {
		return livreRepository.findAll();
	}
	
	
	@GetMapping("/lectures/{username}")
	@PreAuthorize("#username == authentication.principal.username or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Set<Livre>> listeUserLectures(@PathVariable("username") String username) {
		return lecteurRepository.findByUsername(username)
					.map(lect -> new ResponseEntity<>(lect.getLivres(), HttpStatus.OK))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/lectures/{username}/add/{livreId}")
	@PreAuthorize("#username == authentication.principal.username")
	public ResponseEntity<Set<Livre>> ajouterLecture(
			@PathVariable("username") String username,
			@PathVariable("livreId") int livreId) {
		Optional<Lecteur> lect = lecteurRepository.findByUsername(username);

		if (!lect.isPresent())
			return new ResponseEntity<Set<Livre>>(HttpStatus.NOT_FOUND);
		
		Optional<Livre> lvr = livreRepository.findById(livreId);
		if (!lvr.isPresent())
			return new ResponseEntity<Set<Livre>>(HttpStatus.NOT_FOUND);
		
		// ajoute le livre au lecteur
		lect.get().getLivres().add(lvr.get());
		// sauvegarde
		Lecteur saved = lecteurRepository.save(lect.get());
		
		return new ResponseEntity<Set<Livre>>(saved.getLivres(), HttpStatus.OK);
	}
	
	
}
