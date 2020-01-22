package com.edugroupe.mybooksform.metier;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Livre {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String titre;
	@Column(length = 20, unique = true, nullable = false)		private String isbn;
	@ManyToMany(mappedBy = "livres") @JsonIgnore 				private Set<Lecteur> lecteurs;
	

}
