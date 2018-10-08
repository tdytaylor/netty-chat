package com.taylor.netty.codec.response;

import com.taylor.netty.codec.Command;
import com.taylor.netty.codec.DataPacket;
import lombok.Data;
import lombok.ToString;

/**
 * @author tdytaylor
 */
@Data
@ToString
public class ResponseMessage extends DataPacket {

  private String message;
  private boolean isLogin;

  @Override
  public Byte getCommand() {
    return Command.RESPONSE_MESSAGE;
  }
}
