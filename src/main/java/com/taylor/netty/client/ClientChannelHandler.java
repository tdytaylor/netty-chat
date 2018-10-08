package com.taylor.netty.client;

import com.taylor.netty.codec.LoginRequestPacket;
import com.taylor.netty.codec.request.RequestMessage;
import com.taylor.netty.codec.response.ResponseMessage;
import com.taylor.netty.utils.LoginStateUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.UUID;
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
    packet.setId(UUID.randomUUID().toString());
    packet.setLtimestamp(System.currentTimeMillis());
    packet.setName("tangdaiyao");
    packet.setPassword("fdgs454654faf");
    /**
     * 疑问？：直接使用{@link ChannelHandlerContext#writeAndFlush(Object)} 发送数据到服务器端，服务器端接收不到数据
     */
    // ctx.writeAndFlush(packet);
    ctx.channel().writeAndFlush(packet);
    log.info("客户端发送数据完成。");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //ByteBuf buf = (ByteBuf) msg;
//    byte[] bytes = new byte[buf.readableBytes()];
//    buf.readBytes(bytes);
    if (msg instanceof ResponseMessage) {
      ResponseMessage message = (ResponseMessage) msg;
      log.info("{}", message.getMessage());
      if (message.isLogin()) {
        LoginStateUtil.asLogin(ctx.channel());
      }
    }

    RequestMessage message = new RequestMessage();
    message.setMessage("hello server,it's me!, i login last time");
    ctx.channel().writeAndFlush(message);
  }
}
