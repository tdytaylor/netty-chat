package com.taylor.netty.codec;

import static org.junit.Assert.assertTrue;

import com.taylor.netty.utils.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import java.util.UUID;
import org.junit.Test;

public class ByteToDataPacketDecoderTest {

  @Test
  public void test() {
    EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ByteToDataPacketDecoder(),
        new DataPacketToByteEncoder());
    LoginRequestPacket packet = new LoginRequestPacket();
    packet.setId(UUID.randomUUID().toString());
    packet.setLtimestamp(System.currentTimeMillis());
    packet.setName("tangdaiyao");
    packet.setPassword("fdgs454654faf");

    assertTrue(embeddedChannel.writeInbound(packet));
    assertTrue(embeddedChannel.finish());

    ByteBuf buf = embeddedChannel.readOutbound();
    System.out.println(buf.toString(Constants.CHARSET));
  }
}