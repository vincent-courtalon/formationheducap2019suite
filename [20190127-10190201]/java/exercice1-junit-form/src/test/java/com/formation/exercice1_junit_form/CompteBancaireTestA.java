package com.formation.exercice1_junit_form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CompteBancaireTestA {
	
	private CompteBancaire cb1;
	private CompteBancaire cb2;
	private CompteBancaire cb3;
	
	@BeforeEach
	public void beforeTest()
	{
		cb1 = new CompteBancaire(500, 250);
		cb2 = new CompteBancaire(200, 200);
		cb3 = new CompteBancaire(0, 0);
	}
	
	@AfterEach
	public void afterTest() {
		cb1 =null;
		cb2 =null;
		cb3 =null;
	}
	
	@ParameterizedTest(name = " test retrait de {0} sur compte avec 250.0")
	@ValueSource(doubles = {50.0, 100.0, 250.0, 0.0})
	public void testRetraitSimpleOK(double somme) {
		double expected = somme;
		double actual = cb1.retirer(somme);
		assertEquals(expected, actual, "le retrait devrait fonctionner");
	}
	
	@ParameterizedTest(name = " test retrait KO de {0} sur compte avec 200.0")
	@ValueSource(doubles = {300.0, 1000.0, Double.POSITIVE_INFINITY})
	public void testRetraitSimpleKO(double somme) {
		double expected = cb2.getSolde();
		double actual = cb2.retirer(somme);
		assertEquals(expected, actual, "le retrait devrait etre limité au solde");
	}
	
	@ParameterizedTest(name = " test retrait divers de {0} sur compte avec 200.0, resultat attendu {1}, solde final {2}")
	@CsvFileSource(resources = "/resources/testRetraits.csv", numLinesToSkip = 1)
	public void testRetraitsCsv(double somme, double resultat, double solde) {
		double actual = cb2.retirer(somme);
		assertEquals(resultat, actual);
		actual = cb2.getSolde();
		assertEquals(solde, actual);
	}
	
	@ParameterizedTest(name="retrait de {0}")
	@MethodSource("doubleValueProvider")
	public void testRetraitRange(double somme) {
		double expected = somme;
		double actual = cb2.retirer(somme);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest(name="depot de {0}, solde attendu {1}")
	@MethodSource("depotTestProvider")
	public void testDepotMultiple(double somme, double solde) {
		double expected = solde;
		cb1.deposer(somme);
		double actual = cb1.getSolde();
		assertEquals(expected, actual);
	}
	
	
	@ParameterizedTest(name="depot2 de {0}, solde attendu {1}")
	@ArgumentsSource(MyCompteArgumentSource.class)
	public void testDepotMultiple2(double somme, double solde) {
		double expected = solde;
		cb1.deposer(somme);
		double actual = cb1.getSolde();
		assertEquals(expected, actual);
	}
	
	
	static Stream<Arguments> depotTestProvider() {
		return Stream.of(
				arguments(200, 450),
				arguments(100, 350),
				arguments(-100, 250),
				arguments(1000, 500)
				);
	}
	
	
	public static DoubleStream doubleValueProvider() {
		return IntStream.range(5, 150).asDoubleStream();
	}
	
}
