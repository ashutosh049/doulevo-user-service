package com.doulevo.userapi.util.constants;

import java.io.File;

public final class CoreConstants {

  private CoreConstants() {}

  public static class NetworkConstants {
    public static final String ARP = "arp";
    public static final String ARP_A = "a";

    public static final String REQUEST_HEADER = "X-FORWARDED-FOR";

    public static final String LOCALHOST_IPV6_ADDRESS = "0:0:0:0:0:0:0:1";

    public static final String PATTERN_REGEX_FOR_MAC_OR_LINUX =
        "[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+";
    public static final String PATTERN_REGEX_FOR_WINDOWS =
        "[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+-[0-9a-f]+";
  }

  public static class EmailConstants {
    public static final String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$";
    public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTPS_TIMEOUT = "mail.smtps.timeout";
    public static final String MAIL_SMTP_SMARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_DEBUG = "mail.debug";
  }

  public static class HttpConstants {
    public static final String FILE_PATH_SEPERATOR = File.separator;
    public static final String QUERY_PARAMS_INITIALIZER = "?";
    public static final String QUERY_PARAMS_VALUE_INITIALIZER = "=";
    public static final String QUERY_PARAMS_SEPERATOR = "&";
    public static final String ORIGIN = "Origin";
    public static final String HOST = "Host";
    public static final String USER_AGENT = "User-Agent";
    public static final String UNKNOWN = "unknown";
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    public static final String HTTP_X_FORWARDED = "HTTP_X_FORWARDED";
    public static final String HTTP_X_CLUSTER_CLIENT_IP = "HTTP_X_CLUSTER_CLIENT_IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String HTTP_FORWARDED_FOR = "HTTP_FORWARDED_FOR";
    public static final String HTTP_FORWARDED = "HTTP_FORWARDED";
    public static final String HTTP_VIA = "HTTP_VIA";
    public static final String REMOTE_ADDR = "REMOTE_ADDR";


    private static final String[] IP_HEADER_CANDIDATES = {
        X_FORWARDED_FOR,
        PROXY_CLIENT_IP,
        WL_PROXY_CLIENT_IP,
        HTTP_X_FORWARDED_FOR,
        HTTP_X_FORWARDED,
        HTTP_X_CLUSTER_CLIENT_IP,
        HTTP_CLIENT_IP,
        HTTP_FORWARDED_FOR,
        HTTP_FORWARDED,
        HTTP_VIA,
        REMOTE_ADDR
    };
  }


}
