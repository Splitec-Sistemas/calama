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
      jwtService.validateUser(user.getUsername());
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
      String refreshToken = user.getRefreshToken();
      String username = jwtService.validateTokenAndRetrieveSubject(refreshToken);
      jwtService.validateRefreshToken(username, refreshToken);
      String newAccessToken = jwtService.generateToken(username);
      String newRefreshToken = jwtService.generateRefreshToken(username);
      return ResponseEntity.ok(new UserLoginResponse(newAccessToken, newRefreshToken));
    } catch (Exception e) {
      return ResponseEntity.status(401).body(new ErrorResponse(e.getLocalizedMessage(), 401));
    }

  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody UserLoginRequest user) {
    try {
      user.getPassword();
      user.getSkinType();
      user.getUsername();
      /*
         TODO: Criar o usuário cadastrando esses tres parametros acima,
          lembre-se que o username deve ser unico no banco para cada usuário, nao podem existir dois iguais.
       */

      return ResponseEntity.ok("Success");
    } catch (Exception e) {
      return ResponseEntity.status(401).body(new ErrorResponse(e.getLocalizedMessage(), 401));
    }
  }
}