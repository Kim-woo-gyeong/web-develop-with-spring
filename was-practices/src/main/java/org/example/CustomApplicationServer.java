package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


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
            }
        }
    }
}
