package org.splitec.dto;

public class ErrorResponse {
  private String errorMessage;
  private int errorCode;

  public ErrorResponse(String errorMessage, int errorCode){
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}