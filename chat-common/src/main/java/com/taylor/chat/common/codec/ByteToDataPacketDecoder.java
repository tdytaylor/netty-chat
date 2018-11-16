package com.taylor.chat.common.codec;

import com.taylor.chat.common.codec.request.LoginRequestPacket;
import com.taylor.chat.common.codec.request.RequestMessage;
import com.taylor.chat.common.codec.response.ChatMessage;
import com.taylor.chat.common.codec.response.ResponseMessage;
import com.taylor.chat.common.codec.serializer.Serializer;
import com.taylor.chat.common.codec.serializer.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解码类
 *
 * @author tdytaylor
 */
public class ByteToDataPacketDecoder extends ByteToMessageDecoder {

  private final static Map<Byte, Serializer> SERIALIZER_MAP = new HashMap<>(8);
  private final static Map<Byte, Class<? extends DataPacket>> DATA_PACKET_MAP = new HashMap<>(8);

  static {
    SERIALIZER_MAP.put(SerializerAlgorithm.JSON_SERIALIZER, Serializer.DEFAULT);
    DATA_PACKET_MAP.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
    DATA_PACKET_MAP.put(Command.REQUEST_MESSAGE, RequestMessage.class);
    DATA_PACKET_MAP.put(Command.RESPONSE_MESSAGE, ResponseMessage.class);
    DATA_PACKET_MAP.put(Command.RESPONSE_MESSAGE_NORMAL, ChatMessage.class);
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out)
      throws Exception {

    // 跳过 magic number
    byteBuf.skipBytes(4);

    // 跳过版本号
    byteBuf.skipBytes(1);

    // 序列化算法标识
    byte serializeAlgorithm = byteBuf.readByte();

    // 指令
    byte command = byteBuf.readByte();

    // 数据包长度
    int length = byteBuf.readInt();

    byte[] bytes = new byte[length];
    byteBuf.readBytes(bytes);

    Class<? extends DataPacket> requestType = getRequestType(command);
    Serializer serializer = getSerializer(serializeAlgorithm);

    if (requestType != null && serializer != null) {
      out.add(serializer.deserialize(requestType, bytes));
    }
  }

  private Serializer getSerializer(Byte serializeAlgorithm) {
    if (serializeAlgorithm == null || !SERIALIZER_MAP.containsKey(serializeAlgorithm)) {
      return Serializer.DEFAULT;
    }
    return SERIALIZER_MAP.get(serializeAlgorithm);
  }

  private Class<? extends DataPacket> getRequestType(Byte command) {
    if (command == null || !DATA_PACKET_MAP.containsKey(command)) {
      throw new IllegalArgumentException("请求类型为空或者不支持该类型的请求！");
    }
    return DATA_PACKET_MAP.get(command);
  }
}
