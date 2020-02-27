package reflectionform.metier;

import reflectionform.util.PositiveOrZero;
import reflectionform.util.PromptMessage;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private int nbPages;
	private double prix;
	
	
													public int getId() {return id;}
	@PromptMessage(message = "identifiant livre")	public void setId(int id) {this.id = id;}
													public String getTitre() {return titre;}
	@PromptMessage(message = "titre livre")			public void setTitre(String titre) {this.titre = titre;}
													public String getAuteur() {return auteur;}
	@PromptMessage(message = "nom auteur livre")	public void setAuteur(String auteur) {this.auteur = auteur;}
													public int getNbPages() {return nbPages;}
	@PromptMessage(message = "nb Pages livre")
	@PositiveOrZero 								public void setNbPages(int nbPages) {this.nbPages = nbPages;}
													public double getPrix() {return prix;}
													public void setPrix(double prix) {this.prix = prix;}
	
	public Livre() {		
	}
	public Livre(int id, String titre, String auteur, int nbPages, double prix) {
		setId(id);
		setTitre(titre);
		setAuteur(auteur);
		setNbPages(nbPages);
		setPrix(prix);
	}
	
	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", nbPages=" + nbPages + ", prix=" + prix
				+ "]";
	}
	
	
	
	

}
