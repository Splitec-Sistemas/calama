package org.splitec.dto;

import org.splitec.model.DailyPoints;

public class UvExposureInfoRequest {
  private double rssiWifi;
  private double gpsPrecision;
  private double luxValue;
  private String lat;
  private String lon;
  private DailyPoints dailyPoints;


  public DailyPoints getDailyPoints() {
    return dailyPoints;
  }

  public void setDailyPoints(DailyPoints dailyPoints) {
    this.dailyPoints = dailyPoints;
  }

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
