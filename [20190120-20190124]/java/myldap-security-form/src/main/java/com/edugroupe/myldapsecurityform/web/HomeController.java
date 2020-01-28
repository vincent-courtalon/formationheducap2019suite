package com.edugroupe.myldapsecurityform.web;

import java.util.Collections;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	@RolesAllowed({"ROLE_ADMIN"})
	public Map<String, String> hello(Authentication authentication) {
		return Collections.singletonMap("message", "bonjour " 
													+ authentication.getName() 
													+ " " + authentication.getAuthorities());
	}
	
}
