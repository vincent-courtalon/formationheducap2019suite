package com.formation.first_junit_form;

import java.math.BigDecimal;

public class Calculatrice {
	
	public int addition(int x, int y) {
		return x + y;
	}
	
	public int multiplication(int x, int y) {
		return x * y;
	}
	
	public int soustraction(int x, int y) {
		return x - y;
	}
	
	public int division (int x, int y) {
		return x / y;
	}
	
	public double moyenne(double ... values) {
		if (values.length == 0)
			return 0.0;
		double somme = 0.0;
		for (double d : values) {
			somme += d / values.length;
		}
		if (Double.isInfinite(somme))
			return Double.MAX_VALUE;
		return somme; // / values.length;
	}

	public long fibonacci(long n) {
		if (n <= 0) return 0;
		if (n == 1) return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
	
}
