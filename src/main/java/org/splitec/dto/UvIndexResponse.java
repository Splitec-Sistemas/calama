package org.splitec.dto;

public class UvIndexResponse {
  private String latitude;
  private String longitude;
  private double uvIndex;

  public UvIndexResponse(String latitude, String longitude, double uvIndex) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.uvIndex = uvIndex;
  }

  // Getters e setters
  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public double getUvIndex() {
    return uvIndex;
  }

  public void setUvIndex(double uvIndex) {
    this.uvIndex = uvIndex;
  }
}
