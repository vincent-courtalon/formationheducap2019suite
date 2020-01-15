package com.edugroupe.meslectures.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.edugroupe.meslectures.metier.Lecture;
import com.edugroupe.meslectures.metier.Livre;
import com.edugroupe.meslectures.metier.Rating;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@RestController
@RequestMapping("/lectures")
@CrossOrigin
public class LectureController {
	
	
	private final RestTemplate restTemplate;
	
	@Autowired 
	public LectureController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	@GetMapping("/{userId:[a-zA-Z0-9]+}")
	public List<Lecture> findUserLectures(@PathVariable("userId") String userId) {
		// demande la liste des rating de ce userId
		
		Rating[] ratings = restTemplate.getForObject("http://localhost:8484/ratings/" + userId,
					Rating[].class);
		
		// pour chacun de ces rating, récupérer les livres associés
		List<Lecture> lectures = Arrays.stream(ratings)
				.map(rating -> {
				// appel au service livreInfo
					Livre l = restTemplate
						.getForObject("http://localhost:8383/livres/" + rating.getIsbn(),
									   Livre.class);
					return new Lecture(userId, l, rating);
				}).collect(Collectors.toList());
		
		
		// renvoyer le tableau des lectures à l'appelant
		return lectures;
	}

}
