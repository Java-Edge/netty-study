package com.javaedge.netty.ch1.heaetbeat;

import com.javaedge.netty.ch1.heaetbeat.server.DiscardServer;

/**
 * @author JavaEdge
 */
public class Starter {


    //2

    //

    //0-2 + 1 = 3 ___3 //5

    public static void main(String[] args) throws Exception {
        new DiscardServer(9001).run();
    }
}
