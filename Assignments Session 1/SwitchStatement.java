package assignments;

import java.util.Scanner;

public class SwitchStatement {
	
	private static Scanner enteredInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Enter the month days_in_month_dict = January : 1, February: 28, "
				+ "March: 3, April: 4, May: 5, June: 6, July: 7, August: 8, September: 9, "
				+ "October: 10, November: 11, December: 12}");
        int month = enteredInput.nextInt();
        System.out.println("Enter the year");
        int year = enteredInput.nextInt();
        int numDays = 0;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numDays = 30;
                break;
            case 2:
                if ( ((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
                    numDays = 29;
                else
                    numDays = 28;
                break;
            default:
                System.out.println("Invalid month.");
                break;
        }
        
        System.out.println("Number of Days = " + numDays);

	}

}
