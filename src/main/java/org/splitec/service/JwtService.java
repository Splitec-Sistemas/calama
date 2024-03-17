package org.splitec.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.splitec.client.DatabaseClient;
import org.splitec.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Objects;

public class JwtService {
  private static final String SECRET = "ZH9em/GWnV4dkvICU9mTwkC3R/aEcZN50jse71IsbhQ=";
  private static final long EXPIRATION_TIME = 86_400_000; // 1 dia
  private static final long EXPIRATION_TIME_REFRESH = 86_400_000 * 2;
  private static final DatabaseClient databaseClient = new DatabaseClient();

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

  public void validateUser(String username, String password, String refreshToken) throws Exception {
    User user = databaseClient.getUser(username);
    if (user == null || !Objects.equals(user.getPassword(), getHashString(password))) {
      throw new Exception("Usuário ou senha inválidos");
    }
    user.setRefreshToken(refreshToken);
    databaseClient.UpdateUser(user);
  }

  public void validateRefreshToken(String username, String refreshToken, String newRefreshToken) throws Exception {
    User user = databaseClient.getUser(username);
    if (!Objects.equals(user.getRefreshToken(), refreshToken)) {
      throw new Exception("Invalid Refresh Token");
    }
    user.setRefreshToken(newRefreshToken);
    databaseClient.UpdateUser(user);
  }

  public void createUser(String username, String password, int skinType) throws Exception {
    User user = databaseClient.getUser(username);
    if (user != null) {
      throw new Exception("User already exists");
    }
    user = new User(username, getHashString(password), skinType);
    databaseClient.InsertUser(user);
  }

  private String getHashString(String password) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes(),0,password.length());
    return new BigInteger(1,md.digest()).toString(16);
  }
}
