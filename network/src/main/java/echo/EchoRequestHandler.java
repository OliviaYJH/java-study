package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private Socket socket;

	public EchoRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();

			EchoServer.log("connected by client[ip번호: " + remoteHostAddress + ", port번호: " + remotePort + "]");
			EchoServer.log("연결 성공");

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); // parameter
																												// 2개 -
																												// (보조
																												// 스트림,
																												// boolean)
			// OutputStreamWriter() 안에는 주스트림

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")); // 주stream

			while (true) {
				String data = br.readLine();
				if (data == null) {
					EchoServer.log("[server] closed by client");
					break;
				}

				EchoServer.log("received:" + data);
				pw.println(data);
			}
		} catch (IOException e) {
			EchoServer.log("error:" + e);
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
