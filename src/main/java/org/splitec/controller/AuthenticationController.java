package org.splitec.controller;

import org.splitec.dto.UserLoginRequest;
import org.splitec.dto.UserLoginResponse;
import org.splitec.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  @PostMapping("/login")
  public UserLoginResponse login(@RequestBody UserLoginRequest user) {
    JwtService jwtService = new JwtService();
    String token = jwtService.generateToken(user.getUsername());
    String refreshToken = jwtService.generateRefreshToken(user.getUsername());
    return new UserLoginResponse(token, refreshToken);
  }

  @PostMapping("/token/refresh")
  public UserLoginResponse refreshToken(@RequestBody UserLoginRequest user) {
    JwtService jwtService = new JwtService();
    String username = jwtService.validateTokenAndRetrieveSubject(user.getRefreshToken());
    String newAccessToken = jwtService.generateToken(username);
    String newRefreshToken = jwtService.generateRefreshToken(username);
    return new UserLoginResponse(newAccessToken, newRefreshToken);
  }
}