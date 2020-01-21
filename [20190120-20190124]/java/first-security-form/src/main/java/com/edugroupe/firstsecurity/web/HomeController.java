package com.edugroupe.firstsecurity.web;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class HomeController {
	
	/*
	 * Principal permet de récupérer une information minimale sur l'utilisateur loggué
	 * 
	 * injecté automatiquement depuis le contexte de sécurité spring
	 * 
	 * Authentication est plus complet que principal (et le contient)
	 *  injecté aussi depuis le context de sécurité
	 *  
	 *  on peut entre autre verifier ses droits/authorities
	 * 
	 */
	
	@GetMapping
	public Map<String, String> hello(Principal p) {
		System.out.println("utilisateur loggué " +  p.getName());
		return Collections.singletonMap("message",
											"bonjour " + p.getName()
											+ " , le " + LocalDateTime.now());
	}
	
	@GetMapping("/admin")
	public Map<String, String> helloAdmin(Authentication auth ) {
		
		System.out.println(auth.getName() + " " + auth.getAuthorities());
		
		return Collections.singletonMap("message", "bonjour admin, le " + LocalDateTime.now());
	}
	
}
