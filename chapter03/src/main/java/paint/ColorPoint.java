package paint;

public class ColorPoint extends Point {
	private String color;
	
	public ColorPoint(int x, int y, String color) {
//		super.setX(x);
//		super.setY(y);
		
		super(x, y); // 부모의 생성자 사용, 이때 부모 생성자 호출은 반드시 위에 먼저 해야 함 
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		System.out.println("Point[x=" + getX() + ", y=" + getY() + ", color=" + color + "]를 그렸습니다.");
	}

	@Override
	public void draw() {
		show();
	}
	
	
}
