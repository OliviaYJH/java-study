package prob06;

import java.util.Random;
import java.util.Scanner;

public class Sol06 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while(true) {
			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt(100) + 1;
			System.out.println("수를 결정하였습니다. 맞추어 보세요:");
			
			int start = 1, end = 100;
			int count = 1;
			while(start <= end) {
				System.out.println(start + "-" + end);
				System.out.print(count + ">>");
				int userNumber = scanner.nextInt();
				
				if(correctNumber < userNumber) {
					System.out.println("더 낮게");
					end = userNumber;
				} else if(correctNumber > userNumber) {
					System.out.println("더 높게");
					start = userNumber;
				} else {
					// 같은 경우
					System.out.println("맞췄습니다.");
					break;
				}
				count++;
			}
			
			
			System.out.print("다시 하겠습니까?(y/n)>>");
			String answer = scanner.next();
			if("y".equals(answer) == false) {
				break;
			}
		}
		
		scanner.close();
	}
}