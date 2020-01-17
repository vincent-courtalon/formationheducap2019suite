package com.edugroupe.meslectures.metier;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Rating {
	private String userId;
	private String isbn;
	private double note;
	private LocalDate dateCreated;
	
}
