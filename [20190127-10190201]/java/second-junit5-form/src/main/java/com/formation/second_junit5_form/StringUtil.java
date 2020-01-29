package com.formation.second_junit5_form;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private String[] extensionDomainesAutorises;
	// autoriser ou pas com, fr, uk, etc....
	public void seteExtensionDomainesAutorises(String ... domaines) {
		this.extensionDomainesAutorises = domaines.clone();
	}
	
	public StringUtil() {
		extensionDomainesAutorises = new String[] {};
	}
	
	

	public boolean estPalindrome(String chaine) {
		if (chaine == null)
			return false;
		int start = 0;
		int end = chaine.length() - 1;
		while (start < end) {
			if (chaine.charAt(start) != chaine.charAt(end))
				return false;
			start++;
			end--;
		}
		return true;
	}

	public boolean estEmailValide(String email) {
		// logique de controle d'email valide
		boolean match = Pattern.matches("[-a-zA-Z0-9]+([.][-a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.][a-zA-Z0-9]+)*[.][a-zA-Z]{2,7}", email);
		if (!match) return false;
		// verification aussi domaine
		// je sépare l'email via le "."
		
		String[] champs = email.split("[.]");
		// et je recuper ce qui est apres le dernier "."
		String finEmail = champs[champs.length - 1];
		// je compare à tous les extension autorisées
		return Arrays.stream(extensionDomainesAutorises)
					 .anyMatch(ext -> finEmail.equalsIgnoreCase(ext));
	}
	
}
