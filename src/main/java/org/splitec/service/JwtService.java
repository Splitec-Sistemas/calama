package org.splitec.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtService {

  private static final String SECRET = "ZH9em/GWnV4dkvICU9mTwkC3R/aEcZN50jse71IsbhQ=";
  private static final long EXPIRATION_TIME = 86_400_000; // 1 dia
  private static final long EXPIRATION_TIME_REFRESH = 86_400_000 * 2;

  public String generateToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(SECRET));
  }

  public String generateRefreshToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_REFRESH))
        .sign(Algorithm.HMAC512(SECRET.getBytes()));
  }

  public String validateTokenAndRetrieveSubject(String token) {
    return JWT.require(Algorithm.HMAC512(SECRET))
        .build()
        .verify(token)
        .getSubject();
  }
}
