package com.javaedge.netty.ch6.exceptionspread;

import com.javaedge.netty.ch6.BusinessException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author JavaEdge
 */
public class ExceptionCaughtHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // ..

        if (cause instanceof BusinessException) {
            System.out.println("BusinessException");
        }
    }
}
