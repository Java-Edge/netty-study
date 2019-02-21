package com.javaedge.netty.ch9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author JavaEdge
 * @date 2018/11/29
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {

    @Override
    public void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        out.writeShort(msg);
    }
}
