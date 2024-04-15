package org.splitec.dto;

import org.splitec.model.DailyPoints;

import java.util.List;

public class UvHealthPointsResponse {
  private List<DailyPoints> uvHealthPoints;
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  public List<DailyPoints> getUvHealthPoints() {
    return uvHealthPoints;
  }
  public void setUvHealthPoints(List<DailyPoints> uvHealthPoints) {
    this.uvHealthPoints = uvHealthPoints;
  }

  public UvHealthPointsResponse(List<DailyPoints> uvHealthPoints) {
    this.uvHealthPoints = uvHealthPoints;
  }
  public UvHealthPointsResponse(String message) {this.message = message;}
  public UvHealthPointsResponse(List<DailyPoints> uvHealthPoints, String message) {
    this.uvHealthPoints = uvHealthPoints;
    this.message = message;
  }
  public UvHealthPointsResponse() {
  }

}
