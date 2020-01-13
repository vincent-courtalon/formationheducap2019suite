package com.edugroupe.nanomaniaform.metier;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class JeuxVideo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
						private int id;
						private String nom;
						private LocalDate dateSortie;
	@JsonIgnore
	@ManyToMany			private Set<Genre> genres;
	@JsonIgnore
	@ManyToMany 		private Set<PlateForme> plateFormes;
	@JsonIgnore
	@ManyToOne 			private Editeur editeur;
	
	
}
