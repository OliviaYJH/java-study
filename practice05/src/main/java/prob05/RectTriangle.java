package prob05;

public class RectTriangle extends Shape {

	RectTriangle(double width, double height) {
		super(width, height);
	}

	@Override
	double getArea() {
		return width * height * 0.5;
	}

	@Override
	double getPerimeter() {
		return width + height + Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
	}

}
