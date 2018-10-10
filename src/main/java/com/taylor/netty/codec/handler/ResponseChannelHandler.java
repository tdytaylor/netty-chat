package com.taylor.netty.codec.handler;

import com.taylor.netty.codec.request.RequestMessage;
import com.taylor.netty.codec.response.ResponseMessage;
import com.taylor.netty.utils.LoginStateUtil;
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
    log.info("{}", msg.getMessage());
    if (msg.isLogin()) {
      LoginStateUtil.asLogin(ctx.channel());
    }
    RequestMessage message = new RequestMessage();
    message.setMessage("hello server,it's me!, i login last time");
    ctx.channel().writeAndFlush(message);
  }
}
