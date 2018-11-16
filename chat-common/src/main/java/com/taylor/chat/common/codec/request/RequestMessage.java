package com.taylor.chat.common.codec.request;

import com.taylor.chat.common.codec.Command;
import com.taylor.chat.common.codec.DataPacket;
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
