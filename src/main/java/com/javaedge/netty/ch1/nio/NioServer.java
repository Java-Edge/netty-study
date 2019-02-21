package com.javaedge.netty.ch1.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * NIO演示代码
 * @author JavaEdge
 */
public class NioServer {

    /**
     * 保存客户端连接
     */
    static List<SocketChannel> channelList = new ArrayList<>();

    //10w
    //1w
    //
    public static void main(String[] args) throws IOException {

        // 创建NIO ServerSocketChannel，类似BIO的serverSocket
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9001));
        // 设置为非阻塞
        serverSocket.configureBlocking(false);
        System.out.println("服务启动成功");

        while (true) {

            /**
             * 非阻塞模式下，accept方法就不会阻塞了
             * NIO的非阻塞是由os内部实现的，底层调用linux内核accept函数
             */
            SocketChannel socketChannel = serverSocket.accept();

            // 若有客户端进行连接
            if (socketChannel != null) {
                System.out.println("连接成功");
                // 设置SocketChannel为非阻塞
                socketChannel.configureBlocking(false);
                // 保存客户端连接在List中
                channelList.add(socketChannel);
            }
            // 遍历连接进行数据读取 10w - 1000 读写事件
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                // 非阻塞模式read方法不会阻塞，否则会阻塞
                int len = sc.read(byteBuffer);
                // 如果有数据，把数据打印出来
                if (len > 0) {
                    System.out.println(Thread.currentThread().getName() + " 接收到消息：" + new String(byteBuffer.array()));
                } else if (len == -1) {
                    // 若客户端断开，把socket从集合中去掉
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    }
}