package com.taylor.netty.codec;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 传输的数据包
 *
 * @author tdytaylor
 */
@Data
public abstract class DataPacket {

  /**
   * 协议版本
   */
  @JSONField(deserialize = false, serialize = false)
  private Byte version = 1;

  /**
   * 获取数据包的类型（登录....）
   */
  @JSONField(serialize = false)
  public abstract Byte getCommand();
}
