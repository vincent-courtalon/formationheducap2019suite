package com.formation.first_junit_form;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatriceTest {

	// la classe/objet à tester
	private Calculatrice c;
	
	
	// cette méthode sera appelée avant chaque test individuel
	// c'est ce qu'on appel une méthode de setup, ou mise en place
	@Before
	public void beforeTest() {
		System.out.println("avant un test");
		c = new Calculatrice();
	}
	// cette méthode est appelée après chaque test individuel
	// c'est ce qu'on appel une méthode de cleanup, ou nettoyage
	@After
	public void afterTest() {
		System.out.println("après un test");
		c = null;
	}
	
	// en junit4 (et après)
	// une méthode annotée avec @Test est un test unitaire
	// le principe des test est l'utilisation des assertions
	// une assertion vérifie une valeur ou une condition
	// et si elle echoue, l'assertion levera une exception
	// cette exception sera attrapée/gérée par junit
	// Junit fournit en ensemble d'assertion mais c'est extensible
	// d'autres librairies fournissent des assertion plus complexe
	@Test
	public void testAddition() {
		int expected = 36;
		int actual = c.addition(10, 26);
		assertEquals("addition de deux chiffre entier incorrecte", expected, actual);
		/*expected = 10;
		actual = c.addition(5, 6);
		assertEquals("additon de deux chiffre entier incorrecte", expected, actual);*/
	}
	
	@Test
	public void testDivisionA() {
		int expected = 5;
		int actual = c.division(30, 6);
		assertEquals("division de deux chiffre entier incorrecte", expected, actual);
	}

	// ici, on vérifie qu'une division par zero déclenche bien une ArithmeticException
	@Test(expected = ArithmeticException.class)
	public void testDivisionZero() {
		int actual = c.division(5, 0);
	}
	
	@Test
	public void testMoyenneNormale() {
		double expected = 10.0;
		double actual = c.moyenne(5.0, 10.0, 15.0);
		
		assertEquals("la moyenne sur 3 notes ne fonctionne pas", expected, actual, 0.00001);
	}
	
	@Test	
	public void testMoyenneVide() {
		double expected = 0.0;
		double actual = c.moyenne();
		assertEquals("la moyenne vide ne renvoie pas 0", expected, actual, 0.00001);
	}
	
	@Test
	public void testMoyenneMax() {
		double actual = c.moyenne(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		double expected = Double.MAX_VALUE;
		assertEquals("la moyenne avec des max value ne fonctionne pas", expected, actual, 0.00001);
	}
	
	@Test
	public void testFibonacciNormal() {
		long expected = 610;
		long actual = c.fibonacci(15);
		assertEquals("fibonacci ne renvoie pas le bon résultat", expected, actual);
	}
	
	@Test(timeout = 1000)
	@Ignore
	public void testFibonacciLong() {
		long expected = 267914296;
		long actual = c.fibonacci(42);
		assertEquals("fibonacci ne renvoie pas le bon résultat", expected, actual);
	}
	
	
}
