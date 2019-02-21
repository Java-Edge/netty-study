package com.javaedge.netty.ch9;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author JavaEdge
 */
public final class Server {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new Encoder());
                    ch.pipeline().addLast(new BizHandler());
                }
            });

//            new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4);
//
//            new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4);
//
//            new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 4, 4);
//
//            new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 3, 2, 0);
//
//            new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 1, 2, 1, 3);

            ChannelFuture f = b.bind(8888).sync();

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}