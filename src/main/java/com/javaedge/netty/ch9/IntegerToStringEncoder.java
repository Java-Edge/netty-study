package com.javaedge.netty.ch9;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author JavaEdge
 * @date 2018/11/29
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {

    @Override
    public void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
