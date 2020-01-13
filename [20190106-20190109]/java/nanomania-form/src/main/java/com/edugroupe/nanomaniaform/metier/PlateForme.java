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
public class PlateForme {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
											private int id;
											private String nom;
											private String marque;
	@JsonIgnore
	@ManyToMany(mappedBy = "plateFormes") 	private Set<JeuxVideo> jeuxVideos;
	
}
