package com.taylor.chat.common;

import com.taylor.chat.common.codec.request.LoginRequestPacket;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
//    ByteBuf buffer = ctx.channel().alloc().buffer(30);
//    buffer.writeBytes("你好，服务器！".getBytes(Charset.forName("UTF-8")));
    LoginRequestPacket packet = new LoginRequestPacket();
    packet.setTimestamp(System.currentTimeMillis());
    packet.setName("tangdaiyao");
    packet.setPassword("fdgs454654faf");
    /**
     * 疑问？：直接使用{@link ChannelHandlerContext#writeAndFlush(Object)} 发送数据到服务器端，服务器端接收不到数据
     */
    // ctx.writeAndFlush(packet);
    ctx.channel().writeAndFlush(packet);
    log.info("客户端发送数据完成。");
  }
}
