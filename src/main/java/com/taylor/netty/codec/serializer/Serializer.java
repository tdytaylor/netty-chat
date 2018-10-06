package com.taylor.netty.codec.serializer;

/**
 * @author tdytaylor
 */
public interface Serializer {


  Serializer DEFAULT = new JSONSerializer();

  /**
   * 序列化算法
   *
   * @return the serializer algorithm
   */
  byte getSerializerAlgorithm();

  /**
   * java 对象转换成二进制
   */
  byte[] serialize(Object object);

  /**
   * 二进制转换成 java 对象
   */
  <T> T deserialize(Class<T> clazz, byte[] bytes);
}
