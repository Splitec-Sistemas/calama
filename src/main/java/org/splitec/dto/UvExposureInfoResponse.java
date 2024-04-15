package org.splitec.dto;

public class UvExposureInfoResponse {
  private int maxExposureTime;
  private double uv;
  private double uvMax;

  public double getUv() {
    return uv;
  }

  public void setUv(double uv) {
    this.uv = uv;
  }

  public double getUvMax() {
    return uvMax;
  }

  public void setUvMax(double uvMax) {
    this.uvMax = uvMax;
  }

  public int getMaxExposureTime() {
    return maxExposureTime;
  }

  public void setMaxExposureTime(int maxExposureTime) {
    this.maxExposureTime = maxExposureTime;
  }

  public UvExposureInfoResponse(int maxExposureTime) {
    this.maxExposureTime = maxExposureTime;
  }

  public UvExposureInfoResponse() {

  }
}
