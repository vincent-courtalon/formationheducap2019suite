package com.edugroupe.meslectures.web;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.edugroupe.meslectures.metier.Lecture;
import com.edugroupe.meslectures.metier.Livre;
import com.edugroupe.meslectures.metier.Rating;


@RestController
@RequestMapping("/lectures")
@CrossOrigin
public class LectureController {
	
	
	private final RestTemplate restTemplate;
	
	@Value("${microservice.livreinfos.url}")
	private String livreInfoUrl;
	@Value("${microservice.ratings.url}")
	private String ratingInfoUrl;
	
	
	
	@Autowired 
	public LectureController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	@GetMapping("/{userId:[a-zA-Z0-9]+}")
	public List<Lecture> findUserLectures(@PathVariable("userId") String userId) {
		// demande la liste des rating de ce userId
		
		Rating[] ratings = restTemplate.getForObject("http://ratingsinfos/ratings/" + userId,
					Rating[].class);
		
		// copier de la value dans une variable final pour le lambda
		// ici l'url du service livreinfo
		final String livreurl = this.livreInfoUrl;
		
		// pour chacun de ces rating, récupérer les livres associés
		List<Lecture> lectures = Arrays.stream(ratings)
				.map(rating -> {
				// appel au service livreInfo
					Livre l = restTemplate
						.getForObject("http://livresinfos/livres/" + rating.getIsbn(),
									   Livre.class);
					return new Lecture(userId, l, rating);
				}).collect(Collectors.toList());
		
		//LocalDate ld = LocalDate.now();
		//if (ld.getDayOfWeek().equals(DayOfWeek.SATURDAY)
		
		//this.livreInfoUrl = "";
		// renvoyer le tableau des lectures à l'appelant
		return lectures;
	}

}
