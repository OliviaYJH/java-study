package chapter03;

public class StaticMethod {
	int n;
	static int m;
	
	void f1() {
		n = 10;
	}

	void f2() {
		// StaticMethod.m = 10; // 개념적으로 맞는
		
		// 같은 클래스의 static 변수 접근에서 클래스 이름 생략 가능
		m = 20;
	}
	
	void f3() {
		f2();
	}
	
	void f4() {
		StaticMethod.s1();
		
		// 같은 클래스의 static 메소드 접근에서 클래스 이름 생략 가
		s1();
	}
	
	static void s1() {
		// error: static method에서는 instance 변수 접근 불가
		// n = 10;
	}
	
	static void s2() {
		// error: static method에서는 instance 메서드 호출 불
		// f1();
	}
	
	static void s3() {
		StaticMethod.m = 10;
		
		// 같은 클래스의 static 변수 접근에서 클래스 이름 생략 가능
		m = 20;
	}
	
	static void s4() {
		StaticMethod.s1();
		
		// 같은 클래스의 static 메서드 호출에서 클래스 이름 생략 가능
		s1();
	}
}
