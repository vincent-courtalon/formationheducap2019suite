package com.edugroupe.springbootfirstmongo.metier;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {
	@Id		private String id;
			private String nom;
			private int annee;
			private double rating;
	
			
}
