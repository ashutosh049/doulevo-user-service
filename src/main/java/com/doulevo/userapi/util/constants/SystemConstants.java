package com.doulevo.userapi.util.constants;

public final class SystemConstants {

  String EMAIL_REGEXP =
      "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
          + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])"
          + "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:"
          + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
          + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
          + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

  public static class TimeZone {
    public static final String UTC = "UTC";
    public static final String GMT = "GMT";
    public static final String AUSTRALIA_DARWIN = "ACT";
    public static final String AUSTRALIA_SYDNEY = "AET";
    public static final String AMERICA_ARGENTINA_BUENOS_AIRES = "AGT";
    public static final String AFRICA_CAIRO = "ART";
    public static final String AMERICA_ANCHORAGE = "AST";
    public static final String AMERICA_SAO_PAULO = "BET";
    public static final String ASIA_DHAKA = "BST";
    public static final String AFRICA_HARARE = "CAT";
    public static final String AMERICA_ST_JOHNS = "CNT";
    public static final String AMERICA_CHICAGO = "CST";
    public static final String ASIA_SHANGHAI = "CTT";
    public static final String AFRICA_ADDIS_ABABA = "EAT";
    public static final String EUROPE_PARIS = "ECT";
    public static final String AMERICA_INDIANA_INDIANAPOLIS = "IET";
    public static final String ASIA_KOLKATA = "IST";
    public static final String ASIA_TOKYO = "JST";
    public static final String PACIFIC_APIA = "MIT";
    public static final String ASIA_YEREVAN = "NET";
    public static final String PACIFIC_AUCKLAND = "NST";
    public static final String ASIA_KARACHI = "PLT";
    public static final String AMERICA_PHOENIX = "PNT";
    public static final String AMERICA_PUERTO_RICO = "PRT";
    public static final String AMERICA_LOS_ANGELES = "PST";
    public static final String PACIFIC_GUADALCANAL = "SST";
    public static final String ASIA_HO_CHI_MINH = "VST";
  }
}
