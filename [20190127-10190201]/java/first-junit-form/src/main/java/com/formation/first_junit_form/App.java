package com.formation.first_junit_form;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       	Calculatrice c = new Calculatrice();
       	
       	System.out.println(c.addition(3, 4));
		/*BigDecimal bd1 = new BigDecimal(Double.MAX_VALUE);
		BigDecimal bd2 = new BigDecimal(Double.MAX_VALUE);
		bd1 = bd1.add(bd2);
		System.out.println(bd1);*/
       	//System.out.println(c.fibonacci(42));
    }
}
