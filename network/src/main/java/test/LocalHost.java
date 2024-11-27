package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try { // ip 주소 끄집어내기
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostName = inetAddress.getHostName(); 
			String hostIpAddress = inetAddress.getHostAddress(); 
			
			System.out.println(hostName); // yujeonghyeon-ui-MacBookPro.local
			System.out.println(hostIpAddress); // 127.0.0.1
			
			
			
			byte[] IPAddress = inetAddress.getAddress();
			for(byte IPAdresses : IPAddress) { 
				// System.out.println(IPAdresses); // byte 타입 
				System.out.println(IPAdresses & 0x000000ff); // 비트 연산 
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 

	}

}
