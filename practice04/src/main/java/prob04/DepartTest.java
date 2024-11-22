package prob04;

public class DepartTest {
	public static void main(String[] args) { // 상속과 생성자, overriding
		Employee pt = new Depart("홍길동", 3000, "개발부");
		pt.print();
	}
}