package com.taylor.netty.codec.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @author tdytaylor json序列化类：使用fastjson
 */
public class JSONSerializer implements Serializer {

  @Override
  public byte getSerializerAlgorithm() {
    return SerializerAlgorithm.JSON_SERIALIZER;
  }

  @Override
  public byte[] serialize(Object object) {
    if (object == null) {
      throw new IllegalArgumentException("json序列化对象不能为null");
    }
    return JSON.toJSONBytes(object);
  }

  @Override
  public <T> T deserialize(Class<T> clazz, byte[] bytes) {
    return JSON.parseObject(bytes, clazz);
  }
}
