package com.taylor.netty.utils;

import java.nio.charset.Charset;

/**
 * @author tdytaylor
 */
public final class Constants {

  private Constants() {
  }

  public static final Charset CHARSET = Charset.forName("UTF-8");

  public static final String LOGIN_STATUS = "login";
}
