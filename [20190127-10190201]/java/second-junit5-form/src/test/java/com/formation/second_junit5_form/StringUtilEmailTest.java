package com.formation.second_junit5_form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringUtilEmailTest {

	private StringUtil su;
	
	@BeforeEach
	public void beforeTest() {
		su = new StringUtil();
		su.seteExtensionDomainesAutorises(new String[]{"uk", "fr", "de", "com", "org", "net"});
	}
	
	@AfterEach
	public void afterTest() {
		su = null;
	}
	
	@ParameterizedTest(name = "{index} --> email valide: {0}")
	@ValueSource(strings = {"bob@joe.com", "vincent.courtalon@gmail.com", "francois@gov.og.de"})
	public void testEmailOk(String email) {
		assertTrue(su.estEmailValide(email));
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/resources/emails.csv",numLinesToSkip = 1)
	public void testEmaildivers(String email, boolean valide) {
		if (valide)
			assertTrue(su.estEmailValide(email));
		else
			assertFalse(su.estEmailValide(email));
	}
	
	
}
