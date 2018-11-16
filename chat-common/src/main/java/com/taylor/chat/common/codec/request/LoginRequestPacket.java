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
public class LoginRequestPacket extends DataPacket {

  private String name;
  private String password;
  /**
   * 登录时间戳
   */
  private long timestamp;

  @Override
  public Byte getCommand() {
    return Command.LOGIN_REQUEST;
  }
}
