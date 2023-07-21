package org.example;

import org.example.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CustomApplicationServer {
    private final int port;
    private static Logger logger = LoggerFactory.getLogger(CustomApplicationServer.class);
    // 간단하게.. 이렇게 구현해야돼 를 전달하고 싶은게 아닌
    // thread pool 을 통해 안정적인 서비스를 제공한다는게 중요함.
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

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
                 * 문제발생 : 새로운 요청이 오는 경우 , 첫번째 요청이 처리 되야 다음 요청을
                 * 처리 할 수 있음.
                 * */

                /*
                * Step2
                * */
                // new Thread(new ClientRequestHandler(clientSocket)).start();

                /*
                * Step3
                * */
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}
