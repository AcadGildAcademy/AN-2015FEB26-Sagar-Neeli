package assignments;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoopStatement {

	private static int challenge;

	private static void printPattern() {
		int input = IfElse.getInput();

        for (int i = 1; i < input; i++) {
            for (int j = 1; j < i + 1; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
        
        for(int k = input + 1; k > 1; k--) {
            for(int l = 1; l < k - 1; l++){
                System.out.print(l);
            }
            System.out.println();
        }
	}

	private static void primeNumbers() {
		int number = IfElse.getInput();
        System.out.println("Prime numbers between 1 and " + number);
        
        for (int i = 1; i < number; i++) {
        	boolean isPrimeNumber = true;
        	for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrimeNumber = false;
					break;
				}
			}
			if (isPrimeNumber) {
				System.out.print(i + " ");
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter either 1, 2 to run the challenges or 0 to exit:");

		loop: while(userInput.hasNextInt()){
			challenge = userInput.nextInt();
			
			switch (challenge) {
			case 0:
				System.out.println("Loop Statement completed");
				break loop; 

			case 1: 
				System.out.println("Challenge 1");
				primeNumbers();
				break;

			case 2: 
				System.out.println("Challenge 2");
				printPattern();
				break;

			default:
				System.out.println("No such choice");
				break;
			}
		}

	}

}
