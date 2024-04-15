package org.splitec.config;

import org.apache.commons.lang3.StringUtils;

public class CosmosConfig {

  public static final String DATABASE_NAME = "CalamaDB";
  public static final String CONTAINER_NAME = "Users";
  public static String MASTER_KEY =
      System.getProperty("ACCOUNT_KEY",
          StringUtils.defaultString(StringUtils.trimToNull(
                  System.getenv().get("ACCOUNT_KEY")),
              "iLb8ik2J8ernCMnbeXjySyz5tzYIYiLdjZDAcmz3UcpTsSeppTZ2513udfIoRTM0a61jh1nIaOVpACDbNRpQAw=="));

  public static String HOST =
      System.getProperty("ACCOUNT_HOST",
          StringUtils.defaultString(StringUtils.trimToNull(
                  System.getenv().get("ACCOUNT_HOST")),
              "https://yuma.documents.azure.com:443/"));
}
