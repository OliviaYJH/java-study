package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;

import chat.ChatServer;

public class ChatWindow {
	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	// 필드 더 추가해도 됨
	private String nickName;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		nickName = name;
	}

	public void show() {
//		buttonSend.addActionListener((ActionEvent actionEvent) -> {
//			
//		});

		// Textfield
		textField.setColumns(80); // 컬럼 길이
		textField.addKeyListener(new KeyAdapter() { // enter 쳤을 때
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (keyChar == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack(); // UI가 나타남

		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) { // 버튼 눌렀을 때
				sendMessage();
			}
		});

		try {
			// 1. 서버 연결 작업 2. IO Stream 세팅하기
			socket = new Socket();
			socket.connect(new InetSocketAddress("0.0.0.0", ChatServer.PORT));

			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

			log("client start...");
			
			pw.println("join:" + Base64.getEncoder().encodeToString(nickName.getBytes())); // 3. JOIN Protocol
			pw.flush();

			// 4. ChatClientThread 생성
			new ChatClientThread().start();

		} catch (IOException e) {
			log("error:" + e);
		}
	}

	private void sendMessage() {
		String message = textField.getText();

		if ("quit".equals(message)) {
			finish();
		}

		if (!"".equals(message)) {
			pw.println("msg:" + Base64.getEncoder().encodeToString(message.getBytes()));
			pw.flush();
		}

		textField.setText(""); // 비우기
		textField.requestFocus(); // textfield 자동 포커스
	}

	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	private void finish() {
		// quit protocol -> "quit:ok"가 왔을 때

		pw.println("quit");
		pw.flush();

		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			log("error:" + e);
		}

		// exit 할 때
		System.exit(0);
	}

	private void log(String message) {
		System.out.println("[ChatWindow]" + message);
	}

	private class ChatClientThread extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					if (socket.isClosed()) {
						break;
					}

					String data = br.readLine();

					if (data == null || data == "") {
						log("서버가 닫혔습니다.");
					} else if ("join:ok".equals(data)) {
						updateTextArea("입장하였습니다. 즐거운 채팅 되세요.");
					} else {
						updateTextArea(data);
					}

				} catch (IOException e) {
					log("error:" + e);
				}
			}
		}

	}
}
