package org.splitec.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
public class JwtService {

  private static final String SECRET = "testingGabriel";
  private static final long EXPIRATION_TIME = 900_000; // 15 minutos

  public String generateToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(SECRET));
  }

  public String validateTokenAndRetrieveSubject(String token) {
    return JWT.require(Algorithm.HMAC512(SECRET))
        .build()
        .verify(token)
        .getSubject();
  }
}
