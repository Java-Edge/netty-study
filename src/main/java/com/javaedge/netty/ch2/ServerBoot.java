package com.javaedge.netty.ch2;

/**
 * @author JavaEdge
 */
public class ServerBoot {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }
}