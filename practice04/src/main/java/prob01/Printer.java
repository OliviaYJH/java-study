package prob01;

public class Printer {
//	public void println(int i) {
//		show(i);
//	}
//	
//	public void println(boolean d) {
//		show(d);
//	}
//	
//	public void println(double b) {
//		show(b);
//	}
//	
//	public void println(String s) {
//		show(s);
//	}
//	
//	public void show(Object o) {
//		System.out.println(o);
//	}

	public <T> void println(T t) {
		System.out.println(t);
	}

	public <T> void println(T... ts) {
		for (T t : ts) {
			System.out.print(t + " ");
		}
		System.out.println("\n");
	}

	public int sum(Integer... nums) { // 점(.) 3개로 가변 parameter, nums가 parameter을 배열로 받음
		int s = 0;
		for (Integer n : nums) {
			s += n;
		}
		return s;
	}
}
