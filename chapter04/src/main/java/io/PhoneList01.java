package io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {
		Scanner scanner = null;

		try {
			File file = new File("./phone.txt"); // ./: 현재 위치
			if (!file.exists()) {
				System.out.println("File Not Found");
				return;
			}

			// 파일 정보 출력
			System.out.println("== 파일 정보 ==");
			System.out.println(file.getAbsolutePath());
			System.out.println(file.length() + "bytes");
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified()))); // 최근
																													// 수정일

			System.out.println("== 전화번호 ==");

			scanner = new Scanner(file);

			// 4. 처리
			while(scanner.hasNextLine()) { // line 단위 -> 토큰아이징 
				String name = scanner.next();
				String phone01 = scanner.next();
				String phone02 = scanner.next();
				String phone03 = scanner.next();
				
				System.out.println(name + ":" + phone01 + "-" + phone02 + "-" + phone03);
			}
			

		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			scanner.close();
		}

	}

}
