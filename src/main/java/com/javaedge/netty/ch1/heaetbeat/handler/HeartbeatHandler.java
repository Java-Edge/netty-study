package com.javaedge.netty.ch1.heaetbeat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: JavaEdge
 * @version: 1.0
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    int readTimeOut = 0;

    private static final int TIMEOUT_TIMES = 3;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;

        if (event.state() == IdleState.READER_IDLE) {
            readTimeOut++;
        }

        if (readTimeOut >= TIMEOUT_TIMES) {
            System.out.println("超时超过3次，断开连接");
            ctx.close();
        }

        System.out.println("触发了：" + event.state() + "事件" + LocalDateTime.now());

    }
}
