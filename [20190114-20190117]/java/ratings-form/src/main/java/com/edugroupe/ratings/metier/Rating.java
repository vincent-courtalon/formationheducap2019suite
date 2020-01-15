package com.edugroupe.ratings.metier;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Rating {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore								private int id;
	@Column(nullable = false)				private String userId;
	@Column(length = 20,nullable = false)	private String isbn;
											private double note;
											private LocalDate dateCreated;
	
}
