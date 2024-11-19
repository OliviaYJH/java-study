package prob05;

public class Sol05 {
	public static void main(String[] args) {
		for(int i = 1; i <= 100; i++) {
			String n = String.valueOf(i);
			int count = 0;
			for(int j = 0; j < n.length(); j++) {
				if(n.charAt(j) == '3' || n.charAt(j) == '6' || n.charAt(j) == '9') {
					count++;
				}
				if(count > 0) System.out.println(n + " " + "짝".repeat(count) );
			}
		}
	}
}
