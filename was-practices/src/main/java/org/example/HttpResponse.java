package org.example;

import java.io.DataOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpResponse {
	// header 와 body 값을 세팅 해달라고 요청하는 부분.

	private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

	private final DataOutputStream dos;

	public HttpResponse(DataOutputStream dos) {
		this.dos = dos;
	}

	public void response200Headr(String contentType, int lengthOfBodyContent) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			dos.writeBytes("Content-Type: "+ contentType + ";charset=UTF-8 \r\n");
			dos.writeBytes("Content-length:"+ lengthOfBodyContent +" \r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void responseBody(byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.flush();
		}catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
