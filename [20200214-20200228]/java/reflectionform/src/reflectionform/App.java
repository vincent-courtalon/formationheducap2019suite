package reflectionform;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

import reflectionform.metier.Livre;
import reflectionform.metier.Produit;
import reflectionform.util.SuperPromptor;

public class App {

	public static void main(String[] args) {
		
		SuperPromptor<Livre> sp = new SuperPromptor<>(Livre.class);
		
		Livre l1 = sp.saisie();
		
		System.out.println(l1);
		
		
		SuperPromptor<Produit> sp2 = new SuperPromptor<>(Produit.class);
		
		Produit p1 = sp2.saisie();
		System.out.println(p1);
		
		/*String nomClasse = "";
		Scanner input = new Scanner(System.in);
		System.out.println("entrez un nom de classe pleinement qualifié");
		nomClasse = input.nextLine();
		
		try {
			Class classe = Class.forName(nomClasse);
			System.out.println("nom classe:" + classe.getName());
			// getMethods renvoie les méthodes publiques
			Method[] methodes =  classe.getMethods();
			System.out.println("méthodes publiques de la classe");
			for (Method m : methodes) {
				System.out.println(m.getReturnType().getSimpleName() + " "
								+  m.getName() + "("
								+  Arrays.toString(m.getParameterTypes())
								+ ")");
				
			}
			System.out.println("propriétés privée de la classe");
			Field[] fields = classe.getDeclaredFields();
			for (Field f : fields) {
				System.out.println(f.getName() + " "  + f.getType().getSimpleName());
			}
		} catch (ClassNotFoundException e) {e.printStackTrace();}
		
		*/

	}

}
