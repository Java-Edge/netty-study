package com.javaedge.netty.ch1.my;

import com.javaedge.netty.ch1.my.server.DiscardServer;

/**
 * @author JavaEdge
 */
public class Starter {

    public static void main(String[] args) throws Exception {
        new DiscardServer(9002).run();
    }
}
