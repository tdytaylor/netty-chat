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
public class LoginRequestPacket extends DataPacket {

  private String id;
  private String name;
  private String password;
  /**
   * 登录时间戳
   */
  private long ltimestamp;

  @Override
  public Byte getCommand() {
    return Command.LOGIN_REQUEST;
  }
}