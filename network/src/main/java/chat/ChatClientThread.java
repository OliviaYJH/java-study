package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClientThread extends Thread {
	BufferedReader br = null;
	Socket socket = null;

	public ChatClientThread(Socket socket) {
		this.socket = socket;
		try {
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (socket.isClosed()) {
					break;
				}
				String data = br.readLine();
				
				if(data == null || data == "") {
					ChatClient.log("서버가 닫혔습니다.");
				} else if("join:ok".equals(data)) {
					System.out.println("입장하였습니다. 즐거운 채팅 되세요.");
				} else {
					System.out.println(data);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

}
