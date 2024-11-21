package prob02;

public class Goods {
	private String name;
	private int cost;
	private int stock;
	
	public Goods(String name, int cost, int stock) {
		this.name = name;
		this.cost = cost;
		this.stock = stock;
	}
	
	public void printInfo() {
		System.out.println(
				name + "(가격:" + cost + "원)이 " +
	stock + "개 입고 되었습니다."
				);
	}
}