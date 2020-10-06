package com.ubique.javatest;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

class Roots {
	public final double x1, x2;

	public Roots(double x1, double x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
}

public class QuadraticEquation {

	public static Roots findRoots(double a, double b, double c) throws IllegalArgumentException {
		// If a is 0, then equation is not
		// quadratic, but linear

		if (a == 0) {
			System.out.println("Invalid");

		}
		double result1 = 0;
		double result2 = 0;

		double d = b * b - 4 * a * c;
		double sqrt_val = sqrt(abs(d));

		if (d > 0) {

			result1 = (double) (-b + sqrt_val) / (2 * a);
			result2 = (double) (-b - sqrt_val) / (2 * a);

		} else // d < 0
		{
			System.out.println("Roots are complex \n");

			System.out.println(
					-(double) b / (2 * a) + " + i" + sqrt_val + "\n" + -(double) b / (2 * a) + " - i" + sqrt_val);
		}

		return new Roots(result1, result2);
	}

	public static void main(String[] args) {
		Roots roots = QuadraticEquation.findRoots(2, 10, 8);

		try {
			System.out.println("Roots: " + roots.x1 + ", " + roots.x2);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
