package com.javaedge.netty.ch1.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description: nio Selector V2.0
 * @author JavaEdge
 */
public class NioSelectorServer {

    //BIO 1.0   0:看水的人  1:热水壶  2:水开了的事件
    // 2 2 1 1 1 1
    //   0 这人只会遍历，当水开了，才遍历下一个热水壶看是否开了
    // BIO 2.0  为每个热水壶分配一个看水工
    // 1 1 2 1 1 1
    // 0 0 0 0 0 0

    // NIO 1.0
    // 2 2 2 2 2 2
    //           0 看水工遍历检测水是否开了，但不阻塞。然而，即使水都开了，也依旧会继续再遍历所有热水壶
    // NIO 2.0
    //  2 2 2 2 2 2
    //      0      看水工在旁边等着，只要有一个开了，才过去处理

    public static void main(String[] args) throws IOException {

        int OP_ACCEPT = 1 << 4;
        System.out.println(OP_ACCEPT);

        // 创建NIO ServerSocketChannel
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9001));
        // 设置ServerSocketChannel为非阻塞
        serverSocket.configureBlocking(false);

        // 打开Selector处理Channel，即创建epoll，开启一个多路复用器
        Selector selector = Selector.open();
        // 把ServerSocketChannel注册到selector上，并且selector对客户端accept连接操作感兴趣
        SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功");

        while (true) {
            /**
             * 【阻塞】等待需要处理的事件发生 已注册事件发生后，会执行后面逻辑
             *  无事件时，就会一直阻塞
             */
            selector.select();

            /**
             * 获取selector中注册的全部事件的 SelectionKey 实例
             * 即所有的事件集
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            // 遍历SelectionKey，这就是相比 BIO2.0的优化所在：只针对有事件的连接进行处理，无需再每次遍历所有连接
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 如果是OP_ACCEPT事件，则进行连接获取和事件注册
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    // 这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
                    SelectionKey selKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                } else if (key.isReadable()) {
                    // 如果是OP_READ事件，则进行读取和打印
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = socketChannel.read(byteBuffer);
                    // 如果有数据，把数据打印出来
                    if (len > 0) {
                        System.out.println(Thread.currentThread().getName() +  "接收到消息：" + new String(byteBuffer.array()));
                    } else if (len == -1) {
                        // 如果客户端断开连接，关闭Socket
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                }
                //从事件集合里删除本次处理的key，防止下次select重复处理
                iterator.remove();
            }
        }
    }
}