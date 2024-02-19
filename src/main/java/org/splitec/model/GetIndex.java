package org.splitec.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetIndex {
  private Result result;

  public Result getResult() {
    return result;
  }

  public static class Result {
    private double uv;

    @JsonProperty("safe_exposure_time")
    private SafeExposureTime safeExposureTime;

    public SafeExposureTime getSafeExposureTime() {
      return safeExposureTime;
    }

    public double getUv() {
      return uv;
    }

  }

  public static class SafeExposureTime {
    private int st1;
    private int st2;
    private int st3;
    private int st4;
    private int st5;
    private int st6;

    public int getSt1() {
      return st1;
    }

    public int getSt2() {
      return st2;
    }

    public int getSt3() {
      return st3;
    }

    public int getSt4() {
      return st4;
    }

    public int getSt5() {
      return st5;
    }

    public int getSt6() {
      return st6;
    }

    public void setSt1(int st1) {
      this.st1 = st1;
    }

    public void setSt2(int st2) {
      this.st2 = st2;
    }

    public void setSt3(int st3) {
      this.st3 = st3;
    }

    public void setSt4(int st4) {
      this.st4 = st4;
    }

    public void setSt5(int st5) {
      this.st5 = st5;
    }

    public void setSt6(int st6) {
      this.st6 = st6;
    }
  }
}
