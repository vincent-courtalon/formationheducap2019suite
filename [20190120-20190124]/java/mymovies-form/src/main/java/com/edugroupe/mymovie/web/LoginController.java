package com.edugroupe.mymovie.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.mymovie.metier.Utilisateur;
import com.edugroupe.mymovie.repositories.UtilisateurRepository;

@RestController
@RequestMapping("/mylogin")
@CrossOrigin(origins = {"http://localhost:4200"})
public class LoginController {
	
	@Autowired private UtilisateurRepository utilisateurRepository;
	
	@GetMapping
	public ResponseEntity<Utilisateur> LogMeIn(Principal principal) {
		return this.utilisateurRepository.findByLogin(principal.getName())
					.map(u -> new ResponseEntity<>(u, HttpStatus.ACCEPTED))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}
