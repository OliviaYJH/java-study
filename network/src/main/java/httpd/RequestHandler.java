package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Arrays;

public class RequestHandler extends Thread {
	private Socket socket;
	private final String DOCUMENT_ROOT = "./webapp";

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			String request = null;

			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":"
					+ inetSocketAddress.getPort());

			while (true) {
				String line = br.readLine();

				// 브라우저가 연결을 끊으면...
				if (line == null) {
					break;
				}

				// SimpleHttpServer 는 HTTP Header만 읽는다.
				if ("".equals(line)) {
					break;
				}

				// 요청 하나당 스레드 하나로 생각

				// Request Header의 첫 줄만 읽음
				if (request == null) {
					request = line;
					break;
				}

			}

			consoleLog(request);

			String[] tokens = request.split(" ");
			if ("GET".equals(tokens[0])) {
				responseStaticResources(os, tokens[1], tokens[2]);
			} else {
				response400Error(os, tokens[2]);
				// methods: POST, DELETE, PUT, HEAD, CONNECT, ...
				// SimpleHttpServer에서는 무시(400 Bad Request)
			}

		} catch (Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}

			} catch (IOException ex) {
				consoleLog("error:" + ex);
			}
		}
	}

	private void response400Error(OutputStream os, String string) throws IOException {
		/*
		 * HTTP/1.1 400 BAD Request\n
		 * Content-Type: text/html; charset=utf-8\n
		 * \n
		 * 
		 */
		
		File file = new File(DOCUMENT_ROOT + "/error/404.html");
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		os.write((string + " 400 BAD Request\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		os.write(("\n").getBytes());
		os.write(body);
	}

	private void responseStaticResources(OutputStream os, String url, String protocol) throws IOException {
		// default(welcome) file
		if ("/".equals(url)) {
			url = "/index.html";
		}

		File file = new File(DOCUMENT_ROOT + url);
		if (!file.exists()) {
			// 404 response -> 과제 부분
			// 404 응답 
			response404Error(os, protocol);
			return;
		}

		// nid(new id)
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		/*
		 * keep alive: connection 계속 사용하자
		 */
		// 예제 응답입니다.
		// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
		os.write((protocol + " 200 OK\n").getBytes("UTF-8")); // 응답 헤더
		os.write(("Content-Type:" + contentType + ";  charset=utf-8\n").getBytes("UTF-8"));
		os.write("\n".getBytes());
		os.write(body);
	}

	private void response404Error(OutputStream os, String protocol) throws IOException {
		// 여기 과제 
		/*
		 * HTTP/1.1 404 File Not Found\n
		 * Content-Type: text/html; charset=utf-8\n
		 * \n
		 * 404 페이지 내용 
		 */
		File file = new File(DOCUMENT_ROOT + "/error/404.html");
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		
		os.write((protocol + " 404 File Not Found\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		os.write(("\n").getBytes());
		os.write(body);
	}

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}

}
