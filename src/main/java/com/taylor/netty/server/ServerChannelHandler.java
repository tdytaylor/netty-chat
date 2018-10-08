package com.taylor.netty.server;

import com.taylor.netty.codec.LoginRequestPacket;
import com.taylor.netty.codec.request.RequestMessage;
import com.taylor.netty.codec.response.ResponseMessage;
import com.taylor.netty.utils.LoginStateUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Sharable
@Slf4j
public class ServerChannelHandler extends SimpleChannelInboundHandler {

//  @Override
//  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//    ByteBuf buf = (ByteBuf) msg;
//    byte[] bytes = new byte[buf.readableBytes()];
//    buf.readBytes(bytes);
//    log.info("接收到客户端发送的消息：{}", new String(bytes, Charset.forName("UTF-8")));
//    log.info("服务器响应客户端信息：{}", "欢迎！");
//    ctx.writeAndFlush(answer("欢迎！", 20));
//  }
//
//  private ByteBuf answer(String message, int initialCapacity) {
//    ByteBuf buf = Unpooled.buffer(initialCapacity);
//    byte[] bytes = message.getBytes(Constants.CHARSET);
//    buf.writeBytes(bytes);
//    return buf;
//  }


  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg)
      throws Exception {
    if (msg instanceof LoginRequestPacket) {
      LoginRequestPacket loginRequestPacket = (LoginRequestPacket) msg;
      if (vaild(loginRequestPacket)) {
        LoginStateUtil.asLogin(channelHandlerContext.channel());
        ResponseMessage message = new ResponseMessage();
        message.setMessage("登陆成功！");
        channelHandlerContext.channel().writeAndFlush(message);
      }
    }

    if (msg instanceof RequestMessage) {
      RequestMessage message = (RequestMessage) msg;
      Channel channel = channelHandlerContext.channel();
      LoginStateUtil.isLogin(channel);
      log.info("收到已登录客户端信息：{}", message.getMessage());
    }
  }

  private boolean vaild(LoginRequestPacket packet) {
    return true;
  }


}
