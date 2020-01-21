package com.edugroupe.firstsecurity.web;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.firstsecurity.metier.Utilisateur;
import com.edugroupe.firstsecurity.repositories.UtilisateurRepository;

@RestController
@RequestMapping("/infos")
public class InfosController {

	@Autowired private UtilisateurRepository utilisateurRepository;
	
	
	@GetMapping("/greetings")
	//@PreAuthorize("hasRole('ROLE_VISITEUR')")
	@RolesAllowed({"ROLE_VISITEUR"})
	public Map<String, String> greetings(Principal principal) {
		return Collections.singletonMap("message", "bonjour, " + principal.getName());
	}
	
	
	@GetMapping("/user/{username}")
	@PreAuthorize("#username == authentication.principal.username or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Utilisateur> userInfos(@PathVariable("username") String username) {
		return utilisateurRepository.findWithRoleByLogin(username)
									.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
									.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	
}
