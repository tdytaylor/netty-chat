package com.taylor.chat.common.codec.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tdytaylor
 */
@Slf4j
public class LifeCyCleTestHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    log.info("LifeCyCleTestHandler : channelRegistered");
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    log.info("LifeCyCleTestHandler : channelUnregistered");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ctx.fireChannelActive();
    log.info("LifeCyCleTestHandler : channelActive");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("LifeCyCleTestHandler : channelInactive");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    log.info("LifeCyCleTestHandler : channelRead");
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    log.info("LifeCyCleTestHandler : channelReadComplete");
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    log.info("LifeCyCleTestHandler : userEventTriggered");
  }

  @Override
  public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    log.info("LifeCyCleTestHandler : channelWritabilityChanged");
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    log.info("LifeCyCleTestHandler : handlerAdded");
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    log.info("LifeCyCleTestHandler : handlerRemoved");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    log.info("LifeCyCleTestHandler : exceptionCaught");
  }
}
