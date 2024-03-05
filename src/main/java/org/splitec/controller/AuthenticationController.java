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
      //TODO: Adicionar uma validação para identificar se o usuário existe no banco e se as credenciais estão corretas
      JwtService jwtService = new JwtService();
      String token = jwtService.generateToken(user.getUsername());
      String refreshToken = jwtService.generateRefreshToken(user.getUsername());
      return ResponseEntity.ok(new UserLoginResponse(token, refreshToken));
    } catch (Exception e) {
      return ResponseEntity.status(401).body(new ErrorResponse(e.getLocalizedMessage(), 401));
    }

  }

  @PostMapping("/token/refresh")
  public ResponseEntity<Object> refreshToken(@RequestBody UserLoginRequest user) {
    try {
      JwtService jwtService = new JwtService();
      String username = jwtService.validateTokenAndRetrieveSubject(user.getRefreshToken());
      //TODO: Validar se o refresh token enviado está associado ao username
      String newAccessToken = jwtService.generateToken(username);
      String newRefreshToken = jwtService.generateRefreshToken(username);
      return ResponseEntity.ok(new UserLoginResponse(newAccessToken, newRefreshToken));
    } catch (Exception e){
      return ResponseEntity.status(401).body(new ErrorResponse(e.getLocalizedMessage(), 401));
    }

  }
}