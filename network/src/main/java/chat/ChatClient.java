package chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;

		try {
			scanner = new Scanner(System.in);
			socket = new Socket();

			// 연결
			socket.connect(new InetSocketAddress("0.0.0.0", ChatServer.PORT));

			// join 프로토콜
			String nickName = "";
			while (true) {
				System.out.print("닉네임>>");
				nickName = scanner.nextLine();
				if (!"".equals(nickName.trim())) {
					break;
				}
			}
			String encodedNickName = Base64.getEncoder().encodeToString(nickName.getBytes());

			// ChatClientReceiveThread 시작
			var chatThread = new ChatClientThread(socket);
			chatThread.start();

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			pw.println("join:" + encodedNickName);
			pw.flush();

			// 키보드 입력 처리
			while (true) {
				String message = scanner.nextLine();

				if ("quit".equals(message) == true) {
					// quit 프로토콜 처리
					pw.println("quit");
					pw.flush();
					break;
				}

				if (message != "") {
					// 메시지 처리
					pw.println("msg:" + Base64.getEncoder().encodeToString(message.getBytes()));
					pw.flush();
				}

			}
		} catch (IOException ex) {
			log("error:" + ex);
		} finally {
			// 자원 정리
			if (scanner != null)
				scanner.close();
			try {
				if (socket != null && !socket.isClosed())
					socket.close();
			} catch (IOException e) {
				log("error:" + e);
			}
		}
	}

	public static void log(String message) {
		System.out.println("[Chat Server] " + message);
	}
}
