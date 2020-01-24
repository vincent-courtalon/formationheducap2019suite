package com.edugroupe.myinjection.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edugroupe.myinjection.metier.Lecture;
import com.edugroupe.myinjection.repositories.JDBCLivreRepositoryInterface;

@RestController
@RequestMapping("/lectures")
public class LectureController {
	
	@Autowired private JDBCLivreRepositoryInterface jdbcLivreRepository;
	
	@GetMapping("/search")
	public List<Lecture> search(@RequestParam("searchTerm") String searchTerm) {
		return jdbcLivreRepository.findByTitre(searchTerm);
	}
	
	@PutMapping("/changeEmail")
	public Map<String, String> updateEmail(@RequestParam("email") String email,
											@RequestParam("userId") int userId) {
		int nbRowsUpdated = jdbcLivreRepository.updateEmail(email, userId);
		return Collections.singletonMap("update lines", "" + nbRowsUpdated);
	}
	
	
	
}
