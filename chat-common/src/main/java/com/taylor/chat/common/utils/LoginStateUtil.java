package com.taylor.chat.common.utils;


import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * @author tdytaylor
 */
public final class LoginStateUtil {

  private LoginStateUtil() {
  }

  public static final String LOGIN_STATUS = "login";
  public static final AttributeKey LOGIN_STATUS_KEY = AttributeKey.newInstance(LOGIN_STATUS);

  public static void asLogin(Channel channel) {
    channel.attr(LOGIN_STATUS_KEY).set(true);
  }

  public static boolean isLogin(Channel channel) {
    if (channel == null) {
      throw new IllegalArgumentException("channel 不能为空！");
    }
    if (channel.hasAttr(LOGIN_STATUS_KEY)) {
      return (Boolean) channel.attr(LOGIN_STATUS_KEY).get();
    }
    return false;
  }
}
