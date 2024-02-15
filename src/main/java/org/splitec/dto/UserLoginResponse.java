package org.splitec.dto;

public class UserLoginResponse {
  private String token;
  private String refreshToken;

  public UserLoginResponse(String token, String refreshToken) {
    this.token = token;
    this.refreshToken = refreshToken;
  }

  public String getToken() {
    return token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
