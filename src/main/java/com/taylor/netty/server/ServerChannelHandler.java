package com.taylor.netty.server;

import com.taylor.netty.utils.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
    log.info("服务器响应客户端信息：{}", "欢迎！");
    ctx.writeAndFlush(answer("欢迎！", 20));
  }

  private ByteBuf answer(String message, int initialCapacity) {
    ByteBuf buf = Unpooled.buffer(initialCapacity);
    byte[] bytes = message.getBytes(Constants.CHARSET);
    buf.writeBytes(bytes);
    return buf;
  }
}
