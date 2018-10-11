package com.taylor.netty.codec;

import com.taylor.netty.codec.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码类
 *
 * @author tdytaylor
 */
@Sharable
public class DataPacketToByteEncoder extends MessageToByteEncoder<DataPacket> {

  protected static final int MAGIC_NUMBER = 0x12345678;

  @Override
  protected void encode(ChannelHandlerContext ctx, DataPacket packet, ByteBuf byteBuf)
      throws Exception {

    byte[] bytes = Serializer.DEFAULT.serialize(packet);

    byteBuf.writeInt(MAGIC_NUMBER);
    byteBuf.writeByte(packet.getVersion());
    byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
    byteBuf.writeByte(packet.getCommand());
    byteBuf.writeInt(bytes.length);
    byteBuf.writeBytes(bytes);
  }
}
