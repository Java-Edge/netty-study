package com.javaedge.netty.ch6.exceptionspread;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author
 */
public class InBoundHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("InBoundHandlerA.exceptionCaught()");

        ctx.fireExceptionCaught(cause);
    }
}
