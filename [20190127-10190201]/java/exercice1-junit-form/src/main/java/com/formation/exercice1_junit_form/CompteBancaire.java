package com.formation.exercice1_junit_form;

public class CompteBancaire {
	private final double plafond;
	private double solde;
	
	public CompteBancaire(double plafond, double solde) {
		this.plafond = plafond;
		this.solde = solde;
	}

	public double retirer(double somme) {
		if (somme < 0)
			return 0;
		if (somme > solde) {
			somme = solde;
		}
		solde -= somme;
		return somme;
	}
	
	public double deposer(double somme) {
		if (somme < 0)
			return 0;
		if (somme + solde > plafond) {
			// si on veu deposer plus que le plafond
			// ne deposer que jusqu'a arriver au plafond
			somme = plafond - solde;
		}
		solde += somme;
		return somme;
	}
	
	public double getSolde() {
		return this.solde;
	}
	

}
