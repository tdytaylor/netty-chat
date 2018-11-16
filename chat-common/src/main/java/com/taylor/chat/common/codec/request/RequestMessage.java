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

  private String userId;
  private String message;

  public RequestMessage(String userId, String message) {
    this.userId = userId;
    this.message = message;
  }

  public RequestMessage() {
  }

  @Override
  public Byte getCommand() {
    return Command.REQUEST_MESSAGE;
  }
}
