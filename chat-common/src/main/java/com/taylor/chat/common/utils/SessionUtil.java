package com.taylor.chat.common.utils;

import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;

public class SessionUtil {

  private static final Map<String, Channel> SESSION = new HashMap<>(32);

  public static void addSession(String sessionId, Channel channel) {
    SESSION.put(sessionId, channel);
  }

  public static Channel getChannel(String sessionId) {
    return SESSION.get(sessionId);
  }
}
