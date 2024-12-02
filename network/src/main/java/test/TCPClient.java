package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class TCPClient {

	public static void main(String[] args) {
		Socket socket = null;

		try {
			// 1. 소켓 새성
			socket = new Socket();

			// 1-1. 소켓 버퍼 사이즈 확인
			int rcvBufferSize = socket.getReceiveBufferSize();
			int sndBufferSize = socket.getSendBufferSize();
			System.out.println(rcvBufferSize + ": " + sndBufferSize);

			// 1-2. 소켓버퍼 사이즈 변경
			socket.setReceiveBufferSize(1024 * 10);
			socket.setSendBufferSize(1024 * 10);

			rcvBufferSize = socket.getReceiveBufferSize();
			sndBufferSize = socket.getSendBufferSize();
			System.out.println(rcvBufferSize + ": " + sndBufferSize);

			// 1-3. SO_NODELAY(Nagle Algorithm OFF)
			socket.setTcpNoDelay(true);

			// 1-4. SO_TIME)UT
			socket.setSoTimeout(3000);

			// 2. 서버 연결
			socket.connect(new InetSocketAddress("0.0.0.0", 6000)); // ip, port번호 - port 번호는 server와 동일하게

			// 3. IO Stream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// 4. 쓰기
			String data = "Hello World";
			os.write(data.getBytes("utf-8"));

			// 5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);
			if (readByteCount == -1) {
				// 상대편이 명시적으로 close를 호출했을 때 여기로 넘어옴
				System.out.println("[client] closed by server");
				return;
			}

			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received:" + data);

			// 6. 데이터 쓰기 
			// SO_TIMEOUT Test 
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			os.write(data.getBytes("utf-8"));
			
		} catch (SocketTimeoutException e) {
			System.out.println("[client] TimeOut!!!");
		} catch (SocketException e) {
			System.out.println("[client] Socket Exception:" + e);
		} catch (IOException e) {
			System.out.println("[client] error:" + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
