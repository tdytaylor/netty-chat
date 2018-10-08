package com.taylor.netty.client;

import com.taylor.netty.codec.ByteToDataPacketDecoder;
import com.taylor.netty.codec.DataPacketToByteEncoder;
import com.taylor.netty.codec.request.RequestMessage;
import com.taylor.netty.utils.LoginStateUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Slf4j
public class NettyChatClient {

  private static final int MAX_RETRY = 5;

  public static void main(String[] args) {
    NioEventLoopGroup worker = new NioEventLoopGroup(1);
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(worker)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast("decoder", new ByteToDataPacketDecoder());
            ch.pipeline().addLast("custom", new ClientChannelHandler());
            ch.pipeline().addLast("encode", new DataPacketToByteEncoder());
          }
        });

    /*ChannelFuture channelFuture = bootstrap.connect("localhost", 8080).sync()
        .addListener(future -> {
          if (future.isSuccess()) {
            log.info("连接服务端成功！");
          } else {
            int i = 0;
            while (i < MAX_RETRY) {
              bootstrap.connect("localhost", 8080).sync().addListener(future1 -> {
                    if (future1.isSuccess()) {
                      return;
                    }
                  }
              );
            }
          }
        });*/
    connect(bootstrap, "localhost", 8080, MAX_RETRY);

    /*ByteBuf buf = channelFuture.channel().alloc().buffer(20)
        .writeBytes("你好，服务器。".getBytes(Charset.forName("UTF-8")));
    channelFuture.channel().writeAndFlush(buf);*/
  }

  private static void connect(Bootstrap bootstrap, String hostname, int port, int retry) {
    bootstrap.connect(hostname, port).addListener(future -> {
      if (future.isSuccess()) {
        log.info("连接服务器成功。服务器地址：{}，端口：{}", hostname, port);
        startConsoleThread(((ChannelFuture)future).channel());
      } else if (retry == 0) {
        log.info("连接服务器失败。已尝试重新连接{}次。", MAX_RETRY);
      } else {
///        log.info("尝试重新连接服务器。");
//        connect(bootstrap, hostname, port, MAX_RETRY - 1);

        // 第几次重连
        int order = (MAX_RETRY - retry) + 1;
        // 本次重连的间隔
        int delay = 1 << order;
        System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
        // 使用NioEventLoopGroup执行重连，不占用主线程
        bootstrap.config().group()
            .schedule(() -> connect(bootstrap, hostname, port, retry - 1), delay, TimeUnit
                .SECONDS);
      }
    });
  }

  private static void startConsoleThread(Channel channel) {
    new Thread(() -> {
      while (!Thread.interrupted()) {
        if (LoginStateUtil.isLogin(channel)) {
          System.out.println("输入消息发送至服务端: ");
          Scanner sc = new Scanner(System.in);
          String line = sc.nextLine();

          RequestMessage packet = new RequestMessage();
          packet.setMessage(line);
          channel.writeAndFlush(packet);
        }
      }
    }).start();
  }
}
