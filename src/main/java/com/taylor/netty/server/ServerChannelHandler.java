package com.taylor.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Sharable
@Slf4j
public class ServerChannelHandler extends SimpleChannelInboundHandler {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf buf = (ByteBuf) msg;
    byte[] bytes = new byte[buf.readableBytes()];
    buf.readBytes(bytes);
    log.info("接收到客户端发送的消息：{}", new String(bytes, Charset.forName("UTF-8")));
  }
}
