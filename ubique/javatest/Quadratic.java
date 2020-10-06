package com.ubique.javatest;
// Java program to find roots 
//of a quadratic equation 

import static java.lang.Math.abs;
import static java.lang.Math.sqrt; 
class Quadratic 
{ 

	// Prints roots of quadratic 
	//equation ax * 2 + bx + x 
	void findRoots(int a, int b, int c) 
	{ 
	// If a is 0, then equation is not	 
	//quadratic, but linear 
	
	if (a == 0) 
	{ 
		System.out.println("Invalid"); 
		return; 
	} 

	int d = b*b - 4*a*c; 
	double sqrt_val = sqrt(abs(d)); 

	if (d > 0) 
	{ 
		System.out.println("Roots are real and different \n"); 

		System.out.println((double)(-b + sqrt_val) / (2 * a) + "\n"
							+ (double)(-b - sqrt_val) / (2 * a)); 
	} 
	else // d < 0 
	{ 
		System.out.println("Roots are complex \n"); 

		System.out.println( -(double)b / ( 2 * a ) + " + i"
						+ sqrt_val + "\n" + -(double)b / ( 2 * a ) 
						+ " - i" + sqrt_val); 
	} 
	} 

// Driver code 
public static void main(String args[]) 
	{ 
	Quadratic obj = new Quadratic(); 
	int a = 1, b = -7, c = 12; 
	obj.findRoots(a, b, c); 
	} 
} 

//This code is contributed by Anshika Goyal. 
