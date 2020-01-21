package com.edugroupe.firstsecurity.web;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class HomeController {
	
	@GetMapping
	public Map<String, String> hello() {
		return Collections.singletonMap("message", "bonjour, le " + LocalDateTime.now());
	}
	
	@GetMapping("/admin")
	public Map<String, String> helloAdmin() {
		return Collections.singletonMap("message", "bonjour admin, le " + LocalDateTime.now());
	}
	
}
