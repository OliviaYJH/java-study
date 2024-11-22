package paint;

public abstract class Shape implements Drawable { // abstract -> new 불가 
	private Point[] points;
	private String lineColor;
	private String  fillColor;
	
	public abstract void draw();
}
