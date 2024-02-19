package org.splitec.dto;

public class UvExposureInfoRequest {
  private double rssiWifi;
  private double gpsPrecision;
  private double luxValue;
  private String lat;
  private String lon;

  public double getGpsPrecision() {
    return gpsPrecision;
  }

  public String getLat() {
    return lat;
  }

  public String getLon() {
    return lon;
  }

  public double getLuxValue() {
    return luxValue;
  }

  public double getRssiWifi() {
    return rssiWifi;
  }

}
