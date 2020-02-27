package reflectionform.metier;

import reflectionform.util.PositiveOrZero;
import reflectionform.util.PromptMessage;

public class Produit {
	private int id;
	private String nom;
	private double prix;
	private double poids;
	
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poids=" + poids + "]";
	}
	public Produit() {}
	public Produit(int id, String nom, double prix, double poids) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poids = poids;
	}
	public int getId() {
		return id;
	}
	@PromptMessage(order = 1)
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	@PromptMessage(message= "nom du produit",  order = 2)
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	@PromptMessage(message= "prix du produit", order = 4)
	@PositiveOrZero
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getPoids() {
		return poids;
	}
	@PromptMessage(message= "poids du produit", order = 3)
	@PositiveOrZero
	public void setPoids(double poids) {
		this.poids = poids;
	}
	
	

}
