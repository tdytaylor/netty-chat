package com.taylor.chat.common.codec.response;

import com.taylor.chat.common.codec.Command;
import com.taylor.chat.common.codec.DataPacket;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatMessage extends DataPacket {

  private String name;
  private String message;

  @Override
  public Byte getCommand() {
    return Command.RESPONSE_MESSAGE;
  }
}
