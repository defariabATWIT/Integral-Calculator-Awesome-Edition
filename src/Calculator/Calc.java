package Calculator;

import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

// Fun fact: Sharks are older than dinosaurs
public class Calc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int values, exponents, rectangles, place;
		double inputted_value, inputted_exponent;
		List<Double> term_values = new ArrayList<>();
		List<Double> term_exponents = new ArrayList<>();
		List<Double> answers = new ArrayList<>();
		List<Double> trap_answers = new ArrayList<>();
		
		
		double minimum, maximum, sum_type, range_modifier, answer3, placeholder;
		double answer = 0, answer2 = 0, sum = 0, sum_changer = 0, mid = 0, trap = 0;
		
		// Introductory message/ instructions
		System.out.println("This calculator is used to find the Reimann sum of an equation with one variable");
		System.out.println("This one variable is referred to as 'X'");
		System.out.println("Please input the original equation\n");
		
		// Takes in original equation
		System.out.print("How many terms does the original equation have? ");
		values = input.nextInt();
		System.out.print("How many of the " + values + " values have have a variable? ");
		exponents = input.nextInt();
		
		for (int i = 0; i < values; i++) {
			place = i + 1;
			System.out.print("Enter the constant of term number " + place + ": ");
			inputted_value = input.nextInt();
			term_values.add(inputted_value);
			if (exponents > i) {
				System.out.print("Enter the exponent of term number " + place + ": ");
				inputted_exponent = input.nextInt();
				term_exponents.add(inputted_exponent);
			}
		}
		
		// Outputs original equation so you can verify this is my fault and not your own
		System.out.println("The original equation is: ");
		for (int p = 0; p < values; p++) {
			System.out.print(term_values.get(p));
			if (p < exponents) {
				System.out.print("x^" + term_exponents.get(p));
			}
			if (p < values - 1){
				System.out.print(" + ");
			}
		}
		// Unused derivative finder, fully functional, un-comment to use
//		System.out.println("");
//		System.out.println("The derivative of the equation is: ");
//		for (int p = 1; p < term_values.size(); p++) {
//			System.out.print((term_values.get(p - 1) * term_exponents.get(p - 1)));
//			if (p < term_exponents.size()) {
//				System.out.print("x^" + (term_exponents.get(p - 1) - 1) + " + ");
//			}
//		}
		
		// Asks for data related to the integral
		System.out.println("");
		System.out.print("Enter the minimum: ");
		minimum = input.nextDouble();
		System.out.print("Enter the maximum: ");
		maximum = input.nextDouble();
		System.out.print("Enter number of rectangles: ");
		rectangles = input.nextInt();
		
		// "(b - a)/ n" but the name is cooler
		range_modifier = (maximum - minimum)/rectangles;
		
		// This changes one variable that messes with the x variable to give left, right, or mid sum
		System.out.println("Left, Right, Mid-point, Trapezoidal, or Simpson's Reimann's sum?");
		System.out.print("Enter '1', '2', '3', '4', or '5' respectively: ");
		sum_type = input.nextInt();
		
		if(sum_type == 2) {
			sum_changer = 1;
		}else if((sum_type == 3) || (sum_type == 5)){
			sum_changer = 0.5;
		}
		
		// Riemann Sum equation. First loop is each rectangle x point, second loop is each value with that x
		// Each answer is then added into a list
		if(sum_type <= 3 || sum_type == 5) {
			for(int w = 0; w < rectangles; w++) {
				for(int u = 0; u < values; u++) {
					placeholder = minimum + ((w + sum_changer) * range_modifier);
					if(u < exponents) {
						answer = range_modifier * (Math.pow(placeholder, term_exponents.get(u)) * term_values.get(u));
					}else {
						answer = term_values.get(u);
					}
					answers.add(answer);
				}
			}
		}
		
		if(sum_type > 3) {
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
		}
		
		if(sum_type == 5) {
			mid = answers.stream().mapToDouble(Double::doubleValue).sum();
			trap = trap_answers.stream().mapToDouble(Double::doubleValue).sum();
		}
		
		
		// Finds the sum of all answers to obtain the ULTIMATE ANSWER
		if(sum_type <=3) {
			sum = answers.stream().mapToDouble(Double::doubleValue).sum();
		}else if(sum_type == 4) {
			sum = trap_answers.stream().mapToDouble(Double::doubleValue).sum();
		}else if(sum_type == 5){
			sum = ((2 * mid) + trap)/3;
		}
		System.out.println("The Riemann sum is " + sum);
		
	}

}
