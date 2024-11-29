package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class ChatServerThread extends Thread {
	// 1. 스레드의 인스턴스 변수
	private String nickName;
	private Socket socket;
	private List<PrintWriter> printWriters = null;
	private PrintWriter printWriter = null;
	
	public ChatServerThread(Socket socket, List<PrintWriter> printWriters) {
		this.socket = socket;
		this.printWriters = printWriters;
	}

	// 2. 스트림 얻기
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 3. 요청처리
				while(true) {
					String request = br.readLine();
					
					if(request == null) {
						ChatServer.log("클라이언트로부터 연결 끊김");
						doQuit(printWriter);
						break;
					}
					
					// 4. 프로토콜 분석 
					String[] tokens = request.split(":");
					if("join".equals(tokens[0])) {
						doJoin(tokens[1], printWriter);
					} else if("MSG".equals(tokens[0])) {
						doMessage(tokens[1]);
					} else if("quit".equals(tokens[0])) {
						doQuit(printWriter);
					} else {
						ChatServer.log("error: 알 수 없는 요청(" + tokens[0] + ")");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}

	}
	
	private void doQuit(Writer writer) {
		String data = nickName + "님이 퇴장하셨습니다.";
		broadcast(data);
		removeWriter(writer);
	}

	private void removeWriter(Writer writer) {
		synchronized(printWriters) {
			printWriters.remove(writer);
		}
		
	}

	private void doMessage(String string) {
		String data = nickName + " : " + decoding(string);
		broadcast(data);
	}

	private void doJoin(String nickName, PrintWriter writer) {
		this.nickName = decoding(nickName);

		String data = this.nickName + "님이 참여하셨습니다. 즐거운 채팅 되세요";
		broadcast(data);
		
		addWriter(writer); // 참여자 추가 
		
		// ack
		writer.println(data);
		writer.flush();
	}
	
	private String decoding(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

	private void addWriter(PrintWriter writer) { // 참가자 추가
		synchronized(printWriters) {
			printWriters.add(writer);
		}
	}
	
	private void broadcast(String data) { // 내용 추가 
		synchronized(printWriters) {
			for(Writer writer: printWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

}
