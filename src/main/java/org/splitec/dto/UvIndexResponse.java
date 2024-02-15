package org.splitec.dto;

public class UvIndexResponse {
  private double latitude;
  private double longitude;
  private double uvIndex;

  public UvIndexResponse(double latitude, double longitude, double uvIndex) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.uvIndex = uvIndex;
  }

  // Getters e setters
  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getUvIndex() {
    return uvIndex;
  }

  public void setUvIndex(double uvIndex) {
    this.uvIndex = uvIndex;
  }
}
