/*
 * Name:       Daniel O'Connell
 * Assignment: 1
 * Summary:    This program implements a stack to compute 
 * 			   the result of an integer expression in 
 * 			   Reverse Polish Notation (RPN). 
 */

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String rpn = ""; 
		int result = 0;
		System.out.println("\nEnter an RPN Expression:");
		rpn = in.nextLine();
		try{
			result = evaluate(rpn);
			System.out.println("\nThe result of the RPN expression is: " + result);
		}
		catch(ArithmeticException aex){
			System.out.println("\nInvalid operand. \nCannot divide by zero.");
		}
		catch(NumberFormatException nfex){
			System.out.println("\nInvalid operand. \nEnter integer values followed by operators.");
		}
		catch(EmptyStackException esx){
			System.out.println("\nInvalid expression. \nThere should be one less operator than operand.");
		}
		in.close();
	}//end main
	
	public static int evaluate(String str){
		Stack<Integer> operands = new Stack<>();
		int x, y;
		
		//Split the input string into individual characters
		String[] valuesToParse = str.split(" ");
		
		//Iterate over each character of the input string
		for(int i = 0; i < valuesToParse.length; i++){
			if(isOperator(valuesToParse[i])){ //current character is an operand
				y = operands.pop();
				x = operands.pop();
				switch(valuesToParse[i]){
					case "+":
						operands.push(x + y);
						break;
					case "-":
						operands.push(x - y);
						break;
					case "/":
						try{
							operands.push(x/y);
							break;
						}
						catch(ArithmeticException ae){
							throw new ArithmeticException();
						}
					case "x": case "*":
						operands.push(x*y);
						break;
					default:
						System.out.println("\nInvalid operator.");
						break;
				}
			}//end if(isOperator...
			else{ //current character is an operand
				operands.push(Integer.valueOf(valuesToParse[i]));
			}//end else
		}//end for
		return operands.pop();
	}//end evaluate
	
	private static boolean isOperator(String value){
		switch(value){
			case "+":case "-":case "/":case "x": case "*":
				return true;
			default: 
				return false;
		}
	}//end isOperator
}//end Class RPNCalculator
