package reflectionform.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SuperPromptor<T1> {
	
	private Class<T1> t1Classe;
	
	public SuperPromptor(Class<T1> classe) {
		this.t1Classe = classe;
	}
	
	private void saisieDouble(T1 instance, Method m, String message, Scanner input) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean mustBePositive = m.isAnnotationPresent(PositiveOrZero.class);
		while(true) {
			try {
				System.out.println(message);
				double saisie = Double.parseDouble(input.nextLine());
				if (!mustBePositive || saisie >= 0.0) {
					m.invoke(instance, saisie);
					return;
				}
				System.out.println("nombre positif ou nul requis");
			}
			catch( NumberFormatException ex) {
				System.out.println("uniquement un nombre valide svp!");
			}
		}
	}

	private void saisieInt(T1 instance, Method m, String message, Scanner input) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean mustBePositive = m.isAnnotationPresent(PositiveOrZero.class);
		while(true) {
			try {
				System.out.println(message);
				int saisie = Integer.parseInt(input.nextLine());
				if (!mustBePositive || saisie >= 0) {
					m.invoke(instance, saisie);
					return;
				}
				System.out.println("nombre positif ou nul requis");
			}
			catch( NumberFormatException ex) {
				System.out.println("uniquement un nombre valide svp!");
			}
		}
	}
	
	private void saisieString(T1 instance, Method m, String message, Scanner input) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(message);
		String saisie = input.nextLine();
		m.invoke(instance, saisie);
	}

	public T1 saisie() {
		try {
			Scanner input = new Scanner(System.in);
			
			T1 instance = t1Classe.newInstance();
			Method[] methodes = t1Classe.getMethods();
			List<Method> sortedMethods =
				Arrays.stream(methodes)
				  .filter(m -> m.isAnnotationPresent(PromptMessage.class))
				  .sorted((m1, m2) -> 
				  	Integer.compare(m1.getAnnotation(PromptMessage.class).order(),
				  					m2.getAnnotation(PromptMessage.class).order()))
				  .collect(Collectors.toList());
			
			for (Method m : sortedMethods) {
				PromptMessage pm = m.getAnnotation(PromptMessage.class);
				// est ce un setter, qui ne renvoie rien et qui a un seul parametre
				if (m.getName().startsWith("set") && 
					m.getReturnType().equals(void.class) &&
					m.getParameterCount() == 1) {
					Class typeparam = m.getParameterTypes()[0];
					// si c'est un int, double, etc...
					if (typeparam.equals(int.class))
						saisieInt(instance, m, pm.message(), input);
					else if (typeparam.equals(double.class))
						saisieDouble(instance, m, pm.message(), input);	
					else if (typeparam.equals(String.class))
						saisieString(instance, m, pm.message(), input);
				}
			}
			return instance;
		} catch (InstantiationException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (NumberFormatException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		
		return null;
	}
	

}
