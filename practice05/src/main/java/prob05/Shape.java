package prob05;

public abstract class Shape {
	protected double width;
	protected double height;
	
	Shape(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	abstract double getArea();
	abstract double getPerimeter();
}
