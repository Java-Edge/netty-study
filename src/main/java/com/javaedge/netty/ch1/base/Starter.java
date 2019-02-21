package com.javaedge.netty.ch1.base;

import com.javaedge.netty.ch1.base.server.DiscardServer;

/**
 * @author JavaEdge
 */
public class Starter {
    public static void main(String[] args) throws Exception {
        new DiscardServer(9002).run();
    }
}
