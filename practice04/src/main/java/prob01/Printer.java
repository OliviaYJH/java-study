package prob01;

public class Printer {	
	public void println(int i) {
		show(i);
	}
	
	public void println(boolean d) {
		show(d);
	}
	
	public void println(double b) {
		show(b);
	}
	
	public void println(String s) {
		show(s);
	}
	
	public void show(Object o) {
		System.out.println(o);
	}
}
