package assignments;

public class RectangleTriangle {

	public static void main(String[] args) {

		System.out.println("Enter the sides");
		int side1 = IfElse.getInput();
		int side2 = IfElse.getInput();
        Triangle m = new Triangle(side1, side2); 
        System.out.println("Area of Rectangle is : " + m.getArea()); 
        System.out.println("Area of Triangle is : "+ m.getTriangle()); 

	}

}
