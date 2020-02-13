package com.edugroupe.springbootfirstgridfs.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Image {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
	@JsonIgnore												private String storageId;
															private String description;
}
