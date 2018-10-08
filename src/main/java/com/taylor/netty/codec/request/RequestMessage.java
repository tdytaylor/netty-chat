package com.taylor.netty.codec.request;

import com.taylor.netty.codec.Command;
import com.taylor.netty.codec.DataPacket;
import lombok.Data;
import lombok.ToString;

/**
 * @author tdytaylor
 */
@Data
@ToString
public class RequestMessage extends DataPacket {

  private String message;

  @Override
  public Byte getCommand() {
    return Command.REQUEST_MESSAGE;
  }
}
