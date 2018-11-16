package com.taylor.chat.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 屏蔽非自定义协议，增加粘包拆包
 *
 * @author tdytaylor
 */
public class FrameDecoder extends LengthFieldBasedFrameDecoder {

  public FrameDecoder(int maxFrameLength, int lengthFieldOffset,
      int lengthFieldLength) {
    super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
  }

  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    // 屏蔽非本协议的客户端
    if (in.readInt() != DataPacketToByteEncoder.MAGIC_NUMBER) {
      ctx.channel().close();
      return null;
    }
    return super.decode(ctx, in);
  }
}
