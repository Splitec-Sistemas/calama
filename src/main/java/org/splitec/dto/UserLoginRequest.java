package org.splitec.dto;

import org.springframework.util.StringUtils;

public class UserLoginRequest {
  private String username;
  private String password;
  private String refreshToken;

  public String getPassword() {
    if(StringUtils.isEmpty(password)){
      throw new RuntimeException("No password value found. Please proper send the password on request body.");
    } else {
      return password;
    }
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    if(StringUtils.isEmpty(username)){
      throw new RuntimeException("No username value found. Please proper send the username on request body.");
    } else {
      return username;
    }
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
