package com.taylor.chat.common.codec.handler;


import com.taylor.chat.common.codec.request.LoginRequestPacket;
import com.taylor.chat.common.codec.response.ResponseMessage;
import com.taylor.chat.common.utils.LoginStateUtil;
import com.taylor.chat.common.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;


/**
 * @author tdytaylor
 */
@Slf4j
public class LoginChannelHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
    LoginRequestPacket loginRequestPacket = msg;
    System.out.println(loginRequestPacket);
    if (vaild(loginRequestPacket)) {
      LoginStateUtil.asLogin(ctx.channel());
      ResponseMessage message = new ResponseMessage();
      String sessionId = UUID.randomUUID().toString().substring(9);
      SessionUtil.addSession(sessionId, ctx.channel());
      message.setMessage("登陆成功！\n 你的用户id：" + sessionId);
      message.setLogin(true);
      ctx.channel().writeAndFlush(message);
    }
  }

  /**
   * 以后扩展验证用户是否合法
   */
  private boolean vaild(LoginRequestPacket packet) {
    return true;
  }
}
