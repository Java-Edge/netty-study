package com.javaedge.netty.ch2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author JavaEdge
 */
public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server starts success，端口:" + port);
        } catch (IOException exception) {
            System.out.println("Server starts failed");
        }
    }

    public void start() {
        new Thread(() -> doStart()).start();
    }

    private void doStart() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                new ClientHandler(client).start();
            } catch (IOException e) {
                System.out.println("Server failure");
            }
        }
    }
}
