package com.edugroupe.meslectures.metier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Lecture {
	private String userId;
	private Livre livre;
	private Rating rating;

}
