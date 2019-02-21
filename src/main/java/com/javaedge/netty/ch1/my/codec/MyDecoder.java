package com.javaedge.netty.ch1.my.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author JavaEdge
 */
public class MyDecoder extends ByteToMessageDecoder {

    /**
     * 数据长度 + 数据
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }

        // 由于 TCP 流式传输，我们可能一次只获得9999字节数据
        // 但数据长度 4(int) + 10000,才是完整的数据
        // 第二次读数据，这里读了 4 字节
        int i = in.readInt();

        // 查看剩余可读字节
        if (in.readableBytes() < i) {
            in.resetReaderIndex();
            return;
        }

        // 10000
        byte[] data = new byte[i];
        in.readBytes(data);
        System.out.println(new String(data));
        // 10004
        in.markReaderIndex(); // 第二次了，这里更新成 10008,但因为调用了in.resetReaderIndex();这里回到 10004
    }
}
