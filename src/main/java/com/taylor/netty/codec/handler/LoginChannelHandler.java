package com.taylor.netty.codec.handler;

import com.taylor.netty.codec.request.LoginRequestPacket;
import com.taylor.netty.codec.response.ResponseMessage;
import com.taylor.netty.utils.LoginStateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * @author tdytaylor
 */
@Slf4j
public class LoginChannelHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
    LoginRequestPacket loginRequestPacket = (LoginRequestPacket) msg;
    if (vaild(loginRequestPacket)) {
      LoginStateUtil.asLogin(ctx.channel());
      ResponseMessage message = new ResponseMessage();
      message.setMessage("登陆成功！");
      ctx.channel().writeAndFlush(message);
    }
  }

  private boolean vaild(LoginRequestPacket packet) {
    return true;
  }
}
