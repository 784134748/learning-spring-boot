package com.yalonglee.learning.netty.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;


/**
 * @author yalonglee
 */
public class NettyShortConnectionClient {

    private Bootstrap bootstrap;
    private EventLoopGroup workerGroup;

    /**
     * 初始化
     */
    public void init() {
        workerGroup = new NioEventLoopGroup(2, new DefaultThreadFactory("first-netty-tread", true));
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyHttpServerHandler());
            }
        });
    }

    /**
     * 同步写和读(均由Client处理)
     */
    public void writeAndRead() {

    }

    /**
     * 同步写，异步读(由Client写，Server读)
     */
    public void write() {

    }

    /**
     * 连接失败事件
     */
    public void connectionFailEvent() {

    }

    /**
     * 写失败事件
     */
    public void writeFailedEvent() {

    }

    /**
     * 写成功事件
     */
    public void writeSucceedEvent() {

    }

}
