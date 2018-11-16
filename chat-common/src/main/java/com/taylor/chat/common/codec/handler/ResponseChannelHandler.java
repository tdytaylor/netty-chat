package com.taylor.chat.common.codec.handler;


import com.taylor.chat.common.codec.response.ResponseMessage;
import com.taylor.chat.common.utils.LoginStateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Slf4j
public class ResponseChannelHandler extends SimpleChannelInboundHandler<ResponseMessage> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
    if (msg.isLogin()) {
      LoginStateUtil.asLogin(ctx.channel());
    }
    System.out.println(msg.getMessage());
  }
}
