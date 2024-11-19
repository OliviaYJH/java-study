package prob03;

import java.util.Scanner;

public class Sol03 {
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.print("수를 입력 하세요:");
			int number = scan.nextInt();
			if(number == 0) break;
			int sum = 0;
			int start = 0;
			if(number % 2 == 0) {
				start = 2;
			} else {
				start = 1;
			}
			
			for(int i = start; i <= number; i+=2) {
				sum += i;
			}
			System.out.println("결과값: " + sum);
		}
		
		scan.close();
	}
}
