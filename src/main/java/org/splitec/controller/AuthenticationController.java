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
    String token = new JwtService().generateToken(user.getUsername());
    return new UserLoginResponse(token, "refreshTokenTest");
  }
}