package Calculator;

import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

// Fun fact: Sharks are older than dinosaurs
public class Calc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String rule = "Error, incorrect sum type inputted";
		int values, exponents, rectangles, place;
		double inputted_value, inputted_exponent;
		List<Double> term_values = new ArrayList<>();
		List<Double> term_exponents = new ArrayList<>();
		List<Double> exacts = new ArrayList<>();
		List<Double> answers = new ArrayList<>();
		List<Double> trap_answers = new ArrayList<>();
		
		double minimum, maximum, range_modifier, answer3, placeholder, simp, modded_exp, modded_val;
		double answer = 0, answer2 = 0, sum = 0, sum_changer = 0, left = 0, right = 0, mid = 0, trap = 0, exact = 0;
		
		double leftEr, rightEr, midEr, trapEr, simpEr;
		// Introductory message/ instructions
		System.out.println("This calculator is used to find the Riemann sum of an equation with one unique variable.");
		System.out.println("This one variable is referred to as 'X'.");
		System.out.println("Please input the original equation.\n");
		
		// Takes in original equation
		System.out.print("How many terms does the original equation have? (Ex: X + 3 has two values): ");
		values = input.nextInt();
		System.out.print("How many of the " + values + " values have a variable? ");
		exponents = input.nextInt();
		
		for (int i = 0; i < values; i++) {
			place = i + 1;
			if (i == 0) {
				System.out.print("\n(The constant of 'X' is '1') ");
			}
			System.out.printf("Enter the constant of term number %d: ", place);
			inputted_value = input.nextInt();
			term_values.add(inputted_value);
			if (exponents > i) {
				if (i == 0) {
					System.out.print("(The exponent of 'X' is '1') ");
				}
				System.out.printf("Enter the exponent of term number %d: ", place);
				inputted_exponent = input.nextInt();
				term_exponents.add(inputted_exponent);
			}
		}
		
		// Outputs original equation so you can verify this is my fault and not your own
		System.out.println("\nThe original equation is: \n");
		for (int p = 0; p < values; p++) {
			if (p > 0) {
				System.out.print(Math.abs(term_values.get(p)));
			}else {
				System.out.print(term_values.get(p));
			}
			
			if (p < exponents) {
				System.out.print("x^" + term_exponents.get(p));
			}
			if (p < values - 1){
				if (term_values.get(p + 1) >= 0) {
					System.out.print(" + ");
				}else{
					System.out.print(" - ");
				}
			}
		}
		
		// Unused derivative finder, fully functional, un-comment to use
		// System.out.println("");
		// System.out.println("The derivative of the equation is: ");
		// for (int p = 1; p < term_values.size(); p++) {
		//	System.out.print((term_values.get(p - 1) * term_exponents.get(p - 1)));
		//	if (p < term_exponents.size()) {
		//		System.out.print("x^" + (term_exponents.get(p - 1) - 1) + " + ");
		//	}
		//}
		
		// Asks for data related to the integral
		System.out.print("\n\nEnter the lower limit: ");
		minimum = input.nextDouble();
		System.out.print("Enter the upper limit: ");
		maximum = input.nextDouble();
		System.out.print("Enter the number of subintervals: ");
		rectangles = input.nextInt();
		
		// "(b - a)/ n" but the name is cooler
		range_modifier = (maximum - minimum)/rectangles;
		
		// Exact Definite Integral Formula
		for(int u = 0; u < values; u++) {
			if(u < exponents) {
				modded_exp = term_exponents.get(u) + 1;
				modded_val = term_values.get(u) / modded_exp;
				answer = (modded_val * Math.pow(maximum, modded_exp)) - (modded_val * Math.pow(minimum, modded_exp));
			}else {
				answer = (term_values.get(u) * maximum) - (term_values.get(u) * minimum);
			}
			exacts.add(answer);
		}
		
		exact = exacts.stream().mapToDouble(Double::doubleValue).sum();
		
		// Riemann Sum equation. First loop is each rectangle x point, second loop is each value with that x
		// Each answer is then added into a list.

		// Does Lefthand, Righthand, then Midpoint sum
		for(int count = 1; count < 4; count++) {
			if(count == 2) {
				sum_changer = 1;
			}else if(count == 3) {
				sum_changer = 0.5;
			}
		
			for(int w = 0; w < rectangles; w++) {
				for(int u = 0; u < values; u++) {
					placeholder = minimum + ((w + sum_changer) * range_modifier);
					if(u < exponents) {
						answer = range_modifier * Math.pow(placeholder, term_exponents.get(u)) * term_values.get(u);
					}else {
						answer = range_modifier * term_values.get(u);
					}
					answers.add(answer);
				}
			}
			
			sum = answers.stream().mapToDouble(Double::doubleValue).sum();
			
			if(count == 1) {
				left = sum;
			}else if(count == 2) {
				right = sum;
			}else if(count == 3) {
				mid = sum;
			}
			
			// Wipes all data from the list so it can be reused
			answers = new ArrayList<>();
			
		}
		
		// Finds the Trapezoidal sum
		for(int w = 0; w < rectangles; w++) {
			for(int u = 0; u < values; u++) {
				placeholder = minimum + w * range_modifier;
				if(u < exponents) {
					answer = Math.pow(placeholder, term_exponents.get(u)) * term_values.get(u);
				}else {
					answer = term_values.get(u);
				}
				
				placeholder = minimum + (w + 1) * range_modifier;
				if(u < exponents) {
					answer2 = Math.pow(placeholder, term_exponents.get(u)) * term_values.get(u);
				}else {
					answer2 = term_values.get(u);
				}
				
				answer3 = 0.5 * (answer + answer2) * range_modifier;
				trap_answers.add(answer3);
			}
		}
		
		trap = trap_answers.stream().mapToDouble(Double::doubleValue).sum();
		
		// Finds the Simpson's sum by calling the answers for Trapezoidal and Midpoint
		simp = ((2 * mid) + trap)/3;
		
		// Finds percent error of each method
		leftEr = Math.abs((left - exact)/ exact * 100);
		rightEr = Math.abs((right - exact)/ exact * 100);
		midEr = Math.abs((mid - exact)/ exact * 100);
		trapEr = Math.abs((trap - exact)/ exact * 100);
		simpEr = Math.abs((simp - exact)/ exact * 100);
		
		// Prints out results
		System.out.println("\nExact answer:    " + exact);
		System.out.println("\nThe Riemann sums are:\n");
		System.out.println("Lefthand sum:    " + left + ", Percent error: " + leftEr + "%");
		System.out.println("Righthand sum:   " + right + ", Percent error: " + rightEr + "%");
		System.out.println("Midpoint sum:    " + mid + ", Percent error: " + midEr + "%");
		System.out.println("Trapezoidal sum: " + trap + ", Percent error: " + trapEr + "%");
		System.out.println("Simpsons sum:    " + simp +  ", Percent error: " + simpEr + "%");
	}

}
