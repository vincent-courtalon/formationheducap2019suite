package com.edugroupe.myxss.metier;

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
public class Message {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String titre;
																private String corps;

}
