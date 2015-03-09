package assignments;

public class MethodOverloading {

	public static void main(String[] args) {
		
		System.out.println("Enter the length and breadth of rectangle");
		int length = IfElse.getInput();
		int breadth = IfElse.getInput();
		Rectangle rect = new Rectangle(length, breadth);
		System.out.println("Area of Rectangle :" + rect.getArea());
		
		System.out.println("Enter the side of square");
		int side = IfElse.getInput();
		Rectangle square = new Rectangle(side);
		System.out.println("Area of square :" + square.getArea());
		
	}

}
