package com.taylor.chat.common.codec.handler;


import com.taylor.chat.common.codec.request.RequestMessage;
import com.taylor.chat.common.utils.LoginStateUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Slf4j
public class RequestChannelHandler extends SimpleChannelInboundHandler<RequestMessage> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) throws Exception {
    RequestMessage message = (RequestMessage) msg;
    Channel channel = ctx.channel();
    LoginStateUtil.isLogin(channel);
    log.info("收到已登录客户端信息：{}", message.getMessage());
  }
}
