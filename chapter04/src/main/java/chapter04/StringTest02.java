package chapter04;

public class StringTest02 {

	public static void main(String[] args) {
		// immutability(불변성) 
		
		String s1 = "abc";
		String s2 = "def";
		String s3 = s2;
		
		String upperS1 = s1.toUpperCase(); // 내부는 바꿀 수 없다.
		System.out.println(upperS1); // ABC
		System.out.println(s1); // abc
		System.out.println(s2); // def
		System.out.println(s3); // def
		
		s2 = s1.toUpperCase();
		String s4 = s2.concat("??");
		String s5 = "!".concat(s2).concat("@");
		
		System.out.println(s4); // ABC??
		System.out.println(s5); // !ABC@ 
		
		System.out.println(equalsHello("hello")); // true
		System.out.println(equalsHello(null)); // false 
	}
	
	private static boolean equalsHello(String s) {
		return "hello".equals(s); // s.equals("hello");로 하면 equalsHello(null) 에서 NullPointerException 에러 발생 
	}

}
