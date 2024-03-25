package org.splitec.service;

import org.splitec.client.HttpClient;
import org.splitec.model.GetIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class UvService {

  private final HttpClient httpClient;
  int actualReqCount = 0;
  int tokenIndex = 0;
  String[] tokens = {"openuv-4turllogbjxz-io", "openuv-3yquj97rltxly4oc-io", "openuv-32315harlnc70pf4-io",
      "openuv-2lsusjkrlpvs9wpf-io"};

  LocalDateTime lastUpdate = LocalDateTime.now();

  @Autowired
  public UvService(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public GetIndex getUvIndex(String latitude, String longitude) {
    String urlTemplate = UriComponentsBuilder
        .fromUriString("https://api.openuv.io/api/v1/uv")
        .queryParam("lat", "{lat}")
        .queryParam("lng", "{lng}")
        .encode()
        .toUriString();

    Map<String, String> headers = new HashMap<>();
    headers.put("x-access-token", tokenRotation());

    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("lat", latitude);
    uriVariables.put("lng", longitude);

    ResponseEntity<GetIndex> response = (ResponseEntity<GetIndex>) httpClient.getRequest(
        urlTemplate,
        headers,
        uriVariables,
        GetIndex.class);

    return response.getBody();
  }

  private String tokenRotation() {
    if (isResetToken()) {
      this.actualReqCount = 0;
    }
    if (this.actualReqCount > 50) {
      this.actualReqCount = 0;
      resolveTokenIndex();
    }
    this.actualReqCount++;
    return this.tokens[this.tokenIndex];
  }

  private void resolveTokenIndex() {
    if (this.tokenIndex < tokens.length) {
      this.tokenIndex = tokenIndex + 1;
    } else {
      this.tokenIndex = 0;
    }
  }

  public boolean isResetToken() {
    LocalDateTime actual = LocalDateTime.now();
    long days = ChronoUnit.DAYS.between(this.lastUpdate, actual);
    return days >= 1;
  }
}
