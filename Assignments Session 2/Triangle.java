package assignments;

public class Triangle extends Rectangle {

	public Triangle(int a, int b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}
	
    float getTriangle() 
    { 
        return (float)length / (2 * length * breadth); 
    }

}
