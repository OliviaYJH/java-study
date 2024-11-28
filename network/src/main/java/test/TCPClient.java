package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) {
		Socket socket = null;

		try {
			// 1. 소켓 새성
			socket = new Socket();

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
