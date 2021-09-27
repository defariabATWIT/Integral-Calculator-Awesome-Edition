package Calculator;

import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Fun fact: Sharks are older than dinosaurs
public class Calc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int values;
		int exponents;
		
		int place;
		double inputted_value;
		double inputted_exponent;
		List<Double> term_values = new ArrayList<>();
		List<Double> term_exponents = new ArrayList<>();
		
		double range_minimum;
		double range_maximum;
		int rectangles;
		double range_modifier;
		int sum_type;
		double answer;
		double sum;
		List<Double> answers = new ArrayList<>();
		double placeholder;
		double sum_changer = 0;
		
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
		for (int p = 0; p < term_values.size(); p++) {
			System.out.print(term_values.get(p));
			if (p < term_exponents.size()) {
				System.out.print("x^" + term_exponents.get(p) + " + ");
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
		range_minimum = input.nextDouble();
		System.out.print("Enter the maximum: ");
		range_maximum = input.nextDouble();
		System.out.print("Enter number of rectangles: ");
		rectangles = input.nextInt();
		
		// "(b - a)/ n" but the name is cooler
		range_modifier = (range_maximum - range_minimum)/rectangles;
		
		// This changes one variable that messes with the x variable to give left, right, or mid sum
		System.out.println("Left, Right or mid-point Reimann's sum?");
		System.out.print("Enter '1', '2', or '3' respectively: ");
		sum_type = input.nextInt();
		
		if(sum_type == 2) {
			sum_changer = 1;
		}else if(sum_type == 3){
			sum_changer = 0.5;
		}
		
		// Riemann Sum equation. First loop is each rectangle x point, second loop is each value with that x
		// Each answer is then added into a list
		for(int w = 0; w < rectangles; w++) {
			for(int u = 0; u < values; u++) {
				placeholder = range_minimum + ((w + sum_changer) * range_modifier);
				if(u < exponents) {
					answer = Math.pow(placeholder, term_exponents.get(u)) * term_values.get(u);
				}else {
					answer = term_values.get(u);
				}
				answers.add(answer);
			}
		}
		
		// Finds the sum of all answers to obtain the ULTIMATE ANSWER
		sum = answers.stream().mapToDouble(Double::doubleValue).sum();
		System.out.println("The Reimann sum is " + sum);
		
	}

}
