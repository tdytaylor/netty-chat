package com.taylor.netty.codec;

public interface Command {

  /**
   * 登录请求
   */
  byte LOGIN_REQUEST = 1;

  /**
   * 客户端请求
   */
  byte REQUEST_MESSAGE = 2;

  /**
   * 服务端响应
   */
  byte RESPONSE_MESSAGE = 3;
}
