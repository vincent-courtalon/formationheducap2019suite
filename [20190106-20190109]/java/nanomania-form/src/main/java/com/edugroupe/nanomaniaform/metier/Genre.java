package com.edugroupe.nanomaniaform.metier;

import java.util.Set;

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
public class Genre {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
										private int id;
										private String libelle;
	@JsonIgnore
	@ManyToMany(mappedBy = "genres") 	private Set<JeuxVideo> jeuxVideos;
}
