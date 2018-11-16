package com.taylor.chat.common.codec.handler;

import com.taylor.chat.common.codec.response.ChatMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatMessageHandler extends SimpleChannelInboundHandler<ChatMessage> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, ChatMessage msg) throws Exception {
    ChatMessage message = msg;
    System.out.println("----------" + message);
    System.out.println(message.getName() + " -> " + message.getMessage());
  }
}
