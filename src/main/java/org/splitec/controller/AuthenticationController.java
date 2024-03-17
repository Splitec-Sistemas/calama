package org.splitec.controller;

import org.splitec.dto.ErrorResponse;
import org.splitec.dto.UserLoginRequest;
import org.splitec.dto.UserLoginResponse;
import org.splitec.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody UserLoginRequest user) {
    try {
      JwtService jwtService = new JwtService();
      String token = jwtService.generateToken(user.getUsername());
      String refreshToken = jwtService.generateRefreshToken(user.getUsername());
      jwtService.validateUser(user.getUsername(), user.getPassword(), refreshToken);
      return ResponseEntity.ok(new UserLoginResponse(token, refreshToken));
    } catch (Exception e) {
      return ResponseEntity.status(401).body(new ErrorResponse(e.getLocalizedMessage(), 401));
    }
  }

  @PostMapping("/token/refresh")
  public ResponseEntity<Object> refreshToken(@RequestBody UserLoginRequest user) {
    try {
      JwtService jwtService = new JwtService();
      String refreshToken = user.getRefreshToken();
      String username = jwtService.validateTokenAndRetrieveSubject(refreshToken);
      String newAccessToken = jwtService.generateToken(username);
      String newRefreshToken = jwtService.generateRefreshToken(username);
      jwtService.validateRefreshToken(username, refreshToken, newRefreshToken);
      return ResponseEntity.ok(new UserLoginResponse(newAccessToken, newRefreshToken));
    } catch (Exception e) {
      return ResponseEntity.status(401).body(new ErrorResponse(e.getLocalizedMessage(), 401));
    }

  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody UserLoginRequest user) {
    try {
      JwtService jwtService = new JwtService();
      jwtService.createUser(user.getUsername(), user.getPassword(), user.getSkinType());
      return ResponseEntity.ok("Success");
    } catch (Exception e) {
      return ResponseEntity.status(401).body(new ErrorResponse(e.getLocalizedMessage(), 401));
    }
  }
}