package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class FileReaderTest { // 파일 읽기

	public static void main(String[] args) {
		Reader in = null; // 문자 기반 입력 스트림 
		InputStream is = null; // byte 기반 입력 스트림 

		try {
			in = new FileReader("test.txt");
			is = new FileInputStream("test.txt");

			int count = 0;
			int data = -1;
			while ((data = in.read()) != -1) {
				System.out.print((char) data);
				count++;
			}

			System.out.println();
			System.out.println("count: " + count);
			System.out.println("===================");
			
			// 다시 읽기 
			count = 0;
			data = -1;
			
			while((data = is.read()) != -1) {
				System.out.print((char)data);
				count++;
			}
			System.out.println();
			System.out.println("count: " + count);
		} catch (FileNotFoundException e) {
			System.out.println("file not found:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 자원 정리
				if (in != null) {
					in.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
