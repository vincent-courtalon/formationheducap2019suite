package com.edugroupe.springcontact.metier;

public class Contact {
	private String nom;
	private String prenom;
	private String email;
	
	public Contact() {}
	public Contact(String nom, String prenom, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	@Override
	public String toString() {
		return "Contact [nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}

}
