package assignments;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IfElse {

	private static int challenge;
	private static Scanner enteredInput = new Scanner(System.in);


	static int getInput(){
		int input = 0;
		try {
			input = enteredInput.nextInt();
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage() + " Input Mismatch Enter a number");
		}

		return input;
	}


	private static void challengeOneCheckEligibleAge() {
		int age = 0;
		System.out.println("Enter your age:");
		age = getInput();

		if(age <= 0)
			System.out.println("Not a valid number");
		else{
			if (age >= 18) {
				System.out.println("You are eligible to vote");
			} else {
				System.out.println("You are not eligible to vote");
			}
		}
	}


	private static void challengeTwoEvenOrOdd() {
		int number = 0;
		System.out.println("Enter the number to check if its even or odd:");
		number = getInput();

		if (number % 2 == 0) 
			System.out.println("Number is even");
		else
			System.out.println("Number is odd");
	}



	private static void challengeThreeLeapYear() {
		int year = 0;
		System.out.println("Enter the year:");
		year = getInput();

		if((year % 100) == 0) {
			if((year % 400) == 0) {
				System.out.println(year + " is a leap year");
			} else {
				System.out.println(year + "Not a leap year");
			}
		} else {
			if ((year % 4) == 0) {
				System.out.println(year + " is a leap year");
			} else {
				System.out.println(year + " not a leap year");
			}
		}
	}

	public static void main(String args[])
	{

		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter either 1, 2, 3 to run the challenge or 0 to exit:");

		loop: while(userInput.hasNextInt()){
			challenge = userInput.nextInt();
			
			switch (challenge) {
			case 0:
				System.out.println("IfElse completed");
				break loop; 

			case 1: 
				System.out.println("Challenge 1");
				challengeOneCheckEligibleAge();
				break;

			case 2: 
				System.out.println("Challenge 2");
				challengeTwoEvenOrOdd();
				break;

			case 3: 
				System.out.println("Challenge 3");		
				challengeThreeLeapYear();
				break;

			default:
				System.out.println("No such choice");
				break;
			}
		}
	}







}
