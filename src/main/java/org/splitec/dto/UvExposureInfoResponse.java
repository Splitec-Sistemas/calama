package org.splitec.dto;

public class UvExposureInfoResponse {
  private int maxExposureTime;

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
