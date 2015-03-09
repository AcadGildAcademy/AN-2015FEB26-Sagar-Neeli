package assignments;

public class Rectangle {

	int length;
	int breadth;
	
	public Rectangle() {
		this.length = 0;
		this.breadth = 0;
	}

	public Rectangle(int a) {
		length = a;
		breadth = a;
	}
	
	public Rectangle(int a, int b) {
		length = a;
		breadth = b;
	}
	
	public int getArea(){
		return length * breadth;
	}
}
