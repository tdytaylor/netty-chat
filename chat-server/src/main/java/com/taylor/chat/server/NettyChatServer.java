package com.taylor.chat.server;

import com.taylor.chat.common.codec.ByteToDataPacketDecoder;
import com.taylor.chat.common.codec.DataPacketToByteEncoder;
import com.taylor.chat.common.codec.handler.LoginChannelHandler;
import com.taylor.chat.common.codec.handler.MessageRequestHandler;
import com.taylor.chat.common.codec.handler.RequestChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Slf4j
public class NettyChatServer {

  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup dispatcher = new NioEventLoopGroup(1);
    NioEventLoopGroup workers = new NioEventLoopGroup();

    ServerBootstrap serverBootstrap = new ServerBootstrap()
        .group(dispatcher, workers)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG, 1024)
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            // socketChannel.pipeline().addLast(new LifeCyCleTestHandler());
            //socketChannel.pipeline()
               // .addLast(new FrameDecoder(Integer.MAX_VALUE, 7, 4));
            // 处理DataPacket 编解码
            socketChannel.pipeline().addLast(new ByteToDataPacketDecoder());
            // socketChannel.pipeline().addLast(new ServerChannelHandler());
            socketChannel.pipeline().addLast(new LoginChannelHandler());
            socketChannel.pipeline().addLast(new MessageRequestHandler());
            socketChannel.pipeline().addLast(new DataPacketToByteEncoder());
          }
        });

    serverBootstrap.bind("localhost", 8080).sync()
        .addListener(future -> {
          if (future.isSuccess()) {
            log.info("服务器启动成功。端口：{}", 8080);
          }
        });
  }
}
