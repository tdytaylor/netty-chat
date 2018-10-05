package com.taylor.netty.client;

import com.taylor.netty.utils.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Slf4j
@Sharable
public class ClientChannelHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    log.info("客户端开始发送数据。");
    ByteBuf buffer = ctx.channel().alloc().buffer(30);
    buffer.writeBytes("你好，服务器！".getBytes(Charset.forName("UTF-8")));
    ctx.writeAndFlush(buffer);
    log.info("客户端发送数据完成。");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf buf = (ByteBuf) msg;
//    byte[] bytes = new byte[buf.readableBytes()];
//    buf.readBytes(bytes);
    log.info("客户端收到服务器答复：{}", buf.toString(Constants.CHARSET));
  }
}
