package com.taylor.chat.common.codec.serializer;

public interface SerializerAlgorithm {

  /**
   * json 序列化标识
   */
  byte JSON_SERIALIZER = 1;

  Serializer DEFAULT = new JSONSerializer();
}
