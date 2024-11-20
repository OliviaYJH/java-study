package prob02;

import java.util.Scanner;

public class Sol02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int[] intArray = new int[5];
		double sum = 0;

		System.out.println(intArray.length + "개의 숫자를 입력하세요.");
		for(int i = 0; i < intArray.length; i++) {
			intArray[i] = scanner.nextInt();
			sum += intArray[i];
		}
		
		System.out.println("평균은 " + (sum / intArray.length) + "입니다.");
		
		scanner.close();
	}
}
