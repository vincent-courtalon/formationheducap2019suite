package com.formation.second_junit5_form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringUtilTest {

	private StringUtil su;
	
	@BeforeEach   // equivalent au @Before de junit 4
	public void beforeTest() {
		System.out.println("before each");
		su = new StringUtil();
	}
	
	@AfterEach
	public void afterTest() {
		System.out.println("after each");
		su = null;
	}
	
	@Test
	@DisplayName(value = "test de palindrome kayak basique")
	public void testpalindormebasique() {
		String donnee = "kayak";
		assertTrue(su.estPalindrome(donnee), "kayak devrait etre un palindrome");
	}
	
	@ParameterizedTest(name = "numero {index} - avec valeur {0}")
	@ValueSource(strings = {"kayak", "anna", "sos", "radar"})
	@EmptySource
	public void testPalindromeOk(String chaine) {
		assertTrue(su.estPalindrome(chaine));
	}
	
	@ParameterizedTest(name = "numero {index} - avec valeur {0}")
	@ValueSource(strings = {"bonjour", "papa", "java", "test"})
	@NullSource
	public void testPalindromeKo(String chaine) {
		assertFalse(su.estPalindrome(chaine));
	}
	
	
	
}
