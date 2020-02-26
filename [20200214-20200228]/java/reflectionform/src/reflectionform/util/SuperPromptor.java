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
					// si c'est un int
					if (typeparam.equals(int.class)) {
						System.out.println(pm.message());
						m.invoke(instance, Integer.parseInt(input.nextLine()));
					}
					else if (typeparam.equals(double.class)) {
						System.out.println(pm.message());
						m.invoke(instance, Double.parseDouble(input.nextLine()));
					}
					else if (typeparam.equals(String.class)) {
						System.out.println(pm.message());
						m.invoke(instance, input.nextLine());
					}					
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
