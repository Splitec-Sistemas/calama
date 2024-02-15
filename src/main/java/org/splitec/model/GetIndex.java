package org.splitec.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetIndex {
  private Result result;

  // Getters e setters
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public static class Result {
    private double uv;

    // Getters e setters
    public double getUv() {
      return uv;
    }

    public void setUv(double uv) {
      this.uv = uv;
    }
  }
}
