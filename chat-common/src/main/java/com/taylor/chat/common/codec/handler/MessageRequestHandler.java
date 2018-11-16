package com.taylor.chat.common.codec.handler;

import com.taylor.chat.common.codec.request.RequestMessage;
import com.taylor.chat.common.codec.response.ChatMessage;
import com.taylor.chat.common.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理用户聊天信息
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<RequestMessage> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) throws Exception {
    Channel channel = SessionUtil.getChannel(msg.getUserId());
    System.out.println(msg.getUserId() + " : " + msg.getMessage());
    System.out.println(channel);
    ChatMessage message = new ChatMessage();
    message.setName(msg.getUserId());
    message.setMessage(msg.getMessage());
    System.out.println(message.getClass());
    channel.writeAndFlush(message);
  }
}
