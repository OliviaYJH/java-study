package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding)
			/*
			 * Socket에 InetSocketAddress[InetAddress(=IPAddress) + port Number]를 바인딩한다.
			 * IPAddress: 0.0.0.0: 특정 호스트 IP를 바인딩 하지 않는다.
			 * 
			 * 0.0.0.0: 모두가 연결할 수 있도록 함, 연결할 ip를 지정하지 않음 / port number: 5000
			 */
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 6000)); // (connect 할 ip, port number)
			System.out.println("연결 기다림");

			// 3. accept
			Socket socket = serverSocket.accept(); // blocking - 멈춘다. 밑으로 내려가지 않는다. Client로부터 소켓 연결이 올 때까지 대기(accept) 함.
			// data 통신용 socket

			try {
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress(); // 반대편쪽,
				// 다운캐스팅
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();

				System.out.println(
						"[server] connected by client[ip번호: " + remoteHostAddress + ", port번호: " + remotePort + "]");

				System.out.println("연결 성공"); // blocking이 잘 되는지 확인

				// 4. IO Stream 객체 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					// byte로 읽어서 utf-8로 인코딩해야 함.
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking(accept)
					if (readByteCount == -1) {
						// file인 경우에는 끝이지만, socket인 경우에는 닫음을 의미
						// closed by client

						System.out.println("[server] closed by client");
						break;
					}

					// 인코딩
					String data = new String(buffer, 0, readByteCount, "UTF-8"); // 읽을 byte를 직접 지정 중 - 처음부터
																					// readByteCount개
																					// 까지
					System.out.println("[server] receive:" + data);
					
					// 6. 데이터 쓰기 
					os.write(data.getBytes("utf-8")); // string -> byte로 변
					
				}
			} catch (IOException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("[server] error:" + e);
		} finally {
			// 자원 닫기
			try {
				if (serverSocket != null && !serverSocket.isClosed()) { // 두번 닫으면 error 발생하기 때문!
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
