package com.edugroupe.mymovie.metier;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Movie {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String titre;
																private LocalDate dateSortie;
																private int dureeMinutes;
	
}
