package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	public static final int PORT = 6000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket();

			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("연결 기다림 starts...[" + EchoClient.SERVER_IP + ":" + PORT + "]");

			Socket socket = serverSocket.accept();

			try {
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();

				log("connected by client[ip번호: " + remoteHostAddress + ", port번호: " + remotePort + "]");
				log("연결 성공");

				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); // parameter 2개 - (보조 스트림, boolean)
				// OutputStreamWriter() 안에는 주스트림
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")); // 주stream

				while (true) {
					String data = br.readLine();
					if (data == null) {
						log("[server] closed by client");
						break;
					}

					log("received:" + data);
					pw.println(data);
				}
			} catch (IOException e) {
				log("error:" + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SocketException e) {
			log("error:" + e);
		} catch (IOException e) {
			log("error:" + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void log(String message) {
		System.out.println("[Echo Server] " + message);
	}

}
