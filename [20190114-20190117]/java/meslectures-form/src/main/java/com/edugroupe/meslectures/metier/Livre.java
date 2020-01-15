package com.edugroupe.meslectures.metier;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Livre {
	private String isbn;
	private String titre;
	private String description;
	private int nbPages;

}
