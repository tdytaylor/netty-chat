package com.taylor.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Slf4j
public class NettyChatClient {

  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup worker = new NioEventLoopGroup(1);
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(worker)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) throws Exception {
            //ch.pipeline().addLast(new ClientChannelHandler());
          }
        });

    ChannelFuture channelFuture = bootstrap.connect("localhost", 8080).sync()
        .addListener(future -> {
          if (future.isSuccess()) {
            log.info("连接服务端成功！");
          }
        });

    ByteBuf buf = channelFuture.channel().alloc().buffer(20)
        .writeBytes("你好，服务器。".getBytes(Charset.forName("UTF-8")));
    channelFuture.channel().writeAndFlush(buf);
  }
}
