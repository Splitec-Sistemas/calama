package org.splitec.service;

import org.splitec.client.HttpClient;
import org.splitec.dto.UvIndexResponse;
import org.splitec.model.GetIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class UvService {

  private final HttpClient httpClient;

  @Autowired
  public UvService(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public UvIndexResponse getUvIndex(double latitude, double longitude) {
    String urlTemplate = UriComponentsBuilder
        .fromUriString("https://api.openuv.io/api/v1/uv")
        .queryParam("lat", "{lat}")
        .queryParam("lng", "{lng}")
        .encode()
        .toUriString();

    Map<String, String> headers = new HashMap<>();
    headers.put("x-access-token", "openuv-4turllogbjxz-io");

    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("lat", String.valueOf(latitude));
    uriVariables.put("lng", String.valueOf(longitude));

    ResponseEntity<GetIndex> response = (ResponseEntity<GetIndex>) httpClient.getRequest(urlTemplate, headers, uriVariables, GetIndex.class);
    response.getBody().getResult().getUv();
    return new UvIndexResponse(latitude, longitude, response.getBody().getResult().getUv());
  }
}
