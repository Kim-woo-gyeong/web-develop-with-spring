package org.example;

import org.example.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class CustomApplicationServer {
    private final int port;
    private static Logger logger = LoggerFactory.getLogger(CustomApplicationServer.class);
    public CustomApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("[CustomWebApplicationServer] started {} port", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client");
            while((clientSocket = serverSocket.accept()) != null){
                logger.info("[CustomWebApplicationServer] waiting for connected");
                /*
                 * Step1 : 사용자요청을 메인 Therad가 처리한다.
                 * */
                try(InputStream is = clientSocket.getInputStream();
                    OutputStream os = clientSocket.getOutputStream()){

                    BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    OutputStream dos = new DataOutputStream(os);

                    String line;
                    while((line = br.readLine())!=null){
                        System.out.println(line);
                    }

                    HttpRequest httpRequest = new HttpRequest(br);
                    if(httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                    	QueryStrings queryStrings = httpRequest.getQueryStrings();

                    	int op1 = Integer.parseInt(queryStrings.getValue("operand1"));
                    	String op = queryStrings.getValue("operator");
                    	int op2 = Integer.parseInt(queryStrings.getValue("operand2"));

                    	int result = Calculate.calculate(new PositiveNumber(op1), op, new PositiveNumber(op2));

                    	byte[] body = String.valueOf(result).getBytes();

                    	HttpResponse response = new HttpResponse(dos);

                    	response.response200Headr("application/json", body.length);
                    	response.responseBody(body);
                    }
                }
            }
        }
    }
}
