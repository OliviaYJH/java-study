package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			String s = scan.nextLine();
			if("exit".equals(s)) break;
			
			try {
				// InetAddress[] InetAddresses = InetAddress.getAllByName("www.poscodx.com");
				InetAddress[] InetAddresses = InetAddress.getAllByName(s);

				for (InetAddress inetAddress : InetAddresses) {
					System.out.println(inetAddress.getHostName() + " : " + 
							inetAddress.getHostAddress());
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

		}
		
		scan.close();
	}

}
