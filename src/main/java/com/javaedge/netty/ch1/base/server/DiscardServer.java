package com.javaedge.netty.ch1.base.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import com.javaedge.netty.ch1.base.handler.DiscardServerHandler;

/**
 * @author: JavaEdge
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        /**
         * step 1 可简单理解为两个线程池
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // step 2
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    // step 3
                    .channel(NioServerSocketChannel.class)
                    // step 4
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(
//                                    1024,0,
//                                    4,0,4
//                            ));
//                            ch.pipeline().addLast(new LengthFieldPrepender(2));
//                            ch.pipeline().addLast(new StringDecoder());
//                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    // step 5
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // step 6
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            System.out.println("tcp start success");
            // step 7
            ChannelFuture f = b.bind(port).sync();


            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
