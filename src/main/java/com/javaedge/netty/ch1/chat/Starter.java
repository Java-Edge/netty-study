package com.javaedge.netty.ch1.chat;

import com.javaedge.netty.ch1.chat.server.DiscardServer;

/**
 * @author JavaEdge
 */
public class Starter {

    // line
    // header
    // body

    public static void main(String[] args) throws Exception {
        new DiscardServer(9001).run();
    }
}
