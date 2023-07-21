package org.example;

import org.example.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        /* Step2 : 새로운 요청이 올때마다 새로운 Thread 를 생성하여 요청을 처리.
        * 문제 : thread 는 생성 될때마다 독립적인 stack 메모리를 할당 (비싼작업) -> 성능이 매우 떨어짐
        * 그러므로 새로 생성하는게 아닌 thread 를 재활용하는 방식으로 해야함.
        * */
        logger.info("[ClientRequestHandler] new client {} started",Thread.currentThread().getName());
        try(InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream()){

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            DataOutputStream dos = new DataOutputStream(os);

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
        } catch (IOException e){
            logger.error(e.getMessage());
        }
    }
}
