package com.edugroupe.livreinfos.metier;

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
public class Livre {
	@JsonIgnore
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	@Column(length = 20, nullable = false, unique = true) 	private String isbn;
															private String titre;
															private String description;
															private int nbPages;

}
