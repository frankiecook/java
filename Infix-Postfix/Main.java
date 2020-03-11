/**
 * Name: Norman Cook
 * Date: 01/22/2020
 * Description: An infix expression is taken from the user and is converted into a
 * 	postfix form and is evaluated to find the answer to the expression.
 */
import java.util.Scanner;

public class Main {
	/* entry point of program */
	public static void main(String[] args) {
		// start the entryPoint method
		entryPoint();
	}
	
	/* entry point method */
	public static void entryPoint() {
		// setup the scanner and ask the use for the infix expression
		Scanner scanner = new Scanner(System.in);
		String infix;
		System.out.print("Enter infix expression: ");
		infix = scanner.nextLine();
		
		// send infix to the infix to postfix method
		String postfix = infixToPostfix(infix);
		
		// send the postfix for evaluation
		int evalPostfix = evalPostfix(postfix);
		
		// display the summary
		System.out.println("Summary");
		System.out.println("-------");
		System.out.println("  Infix: " + infix);
		System.out.println("Postfix: " + postfix);
		//System.out.println("Back to Infix: (" + infix + ")");
		System.out.println(" Result: " + evalPostfix);
		
		if (scanner != null) {
			scanner.close();
		}
	}
	
	/* assign an infix priority for the character */
	public static int getInfixPriority(char c) {
		// ( returns priority 4
		if (c=='(') {
			return 4;
		}
		
		// ^ returns priority 3
		else if (c=='^') {
			return 3;
		}
		
		// * or / returns priority 2
		else if (c=='*' || c=='/') {
			return 2;
		}
		
		// + or - returns priority 1
		else if (c=='+' || c=='-') {
			return 1;
		}
		
		return 0;
	}
	
	/* check the stack priority of the character */
	public static int getStackPriority(char c) {
		// ^ or * or / returns 2
		if (c=='^' || c=='*' || c=='/') {
			return 2;
		}
		
		// + or - returns priority 1
		else if (c=='+' || c=='-') {
			return 1;
		}
		return 0;
	}
	
	/* check if the character is an operand (0-9) */
	public static boolean isOperand(char c) {
		// return true if the character is a number from 0-9
		if (c=='0' ||
			c=='1' ||
			c=='2' ||
			c=='3' ||
			c=='4' ||
			c=='5' ||
			c=='6' ||
			c=='7' ||
			c=='8' ||
			c=='9') {
			return true;
		}
		
		return false;
	}
	
	/* evaluate the mathematical operation of the given terms */
	public static int eval(char operator, int a, int b) {
		// multiply the integers
		if (operator == '+') {
			return a + b;
		} 
		
		// subtract the integers
		else if (operator == '-') {
			return a - b;
		} 
		
		// multiply the integers
		else if (operator == '*') {
			return a * b;
		} 
		
		// divide the integers
		else if (operator == '/') {
			return a / b;
		} 
		
		// exponential operation
		else if (operator == '^'){
			int x = 1;
			int y = b;
			
			while (y > 0) {
				x *= a;
				y--;
			}
			return x;
		} else {
			return -1;
		}
	}
	
	/* transform an infix String to a postfix String */
	public static String infixToPostfix(String infixString) {
		// declare new queues and stacks
		Queue<Character> postfixQueue = new Queue<Character>();
		Stack<Character> operatorStack = new Stack<Character>();
		Queue<Character> infixQueue = new Queue<Character>();
		
		// declare variables
		char token;
		char operator;
		String postfix = "";
		
		// enqueue the infix string to the infix queue
		for (int i=0; i<infixString.length(); i++) {
			infixQueue.enqueue(infixString.charAt(i));
		}
		
		
		while (!infixQueue.isEmpty()) {
			// grab the next character
			token = infixQueue.dequeue();
			
			// process operands
			if (isOperand(token)) {
				postfixQueue.enqueue(token);
			}
			
			
			// process parenthesis
			else if (token == ')') {
				operator = operatorStack.pop();
				while (operator != '(') {
					postfixQueue.enqueue(operator);
					operator = operatorStack.pop();
				}
			}
			
			// process operators (not right parenthesis)
			else {
				if (!operatorStack.isEmpty()) {
					operator = operatorStack.peek();
					
					// move operators with high priority into our postfixQueue
					while (getStackPriority(operator) >= getInfixPriority(token)) {
						operator = operatorStack.pop();
						postfixQueue.enqueue(operator);
						
						// are there more operators on the operatorStack
						if (!operatorStack.isEmpty()) {
							operator = operatorStack.peek();
						} else {
							break;
						}
					}
				}
				
				// push our new operator
				operatorStack.push(token);
			}
		}
		
		// NOTE: this is outside of our while loop above
		// this will unload the operator stack onto our postfixqueue
		while (!operatorStack.isEmpty()) {
			operator = operatorStack.pop();
			postfixQueue.enqueue(operator);
		}
		
		// transfer the postfixQueue contents into a string
		while (!postfixQueue.isEmpty()) {
			postfix += String.valueOf(postfixQueue.dequeue());
		}
		
		
		// return the postfix string
		return postfix;
	}
	
	/* evauluate the mathematical operation of the post fix expression */
	public static int evalPostfix(String postfixString) {
		// declare new stack and queue
		Stack<Integer> evalStack = new Stack<Integer>();
		Queue<Character> postfixQueueEval = new Queue<Character>();
		
		// declare variables	
		char token;
		int answer;
		int a;
		int b;
		
		//enqueue each character into postfixQueue
		for (int i=0; i<postfixString.length(); i++) {
			postfixQueueEval.enqueue(postfixString.charAt(i));
		}
		
		// process the postfixQueue
		while (!postfixQueueEval.isEmpty()) {
			token = postfixQueueEval.dequeue();

			// if token is a digit, throw it on the evalStack
			if (isOperand(token)) {
				evalStack.push(Character.getNumericValue(token));
			}
			
			// otherwise, evaluate our sub-expression and
			// push the answer onto the evalStack
			else {
				a = evalStack.pop();
				b = evalStack.pop();
				answer = eval(token, b, a);
				evalStack.push(answer);
			}
		}
		
		// if our evalStack is not empty after processing everything, then
		// return the final answer stored at the top
		if (!evalStack.isEmpty()) {
			return evalStack.pop();
		} else {
			return 000000;
		}
	}
	
}