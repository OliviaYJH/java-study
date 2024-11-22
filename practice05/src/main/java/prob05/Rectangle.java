package prob05;

public class Rectangle extends Shape implements Resizable {
	
	Rectangle(double width, double height) {
		super(width, height);
	}

	@Override
	double getArea() {
		return width * height;
	}

	@Override
	double getPerimeter() {
		return ( width + height )* 2;
	}

	@Override
	public void resize(double d) {
		width = width * d;
		height = height * d;
	}
	
	

}
