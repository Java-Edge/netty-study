package com.javaedge.netty.ch1.my.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JavaEdge
 */
public class DiscardServerHandler  extends ChannelInboundHandlerAdapter {

    static Set<Channel> channelList = new HashSet<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {



    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }
}
