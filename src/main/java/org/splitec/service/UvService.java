package org.splitec.service;

import org.splitec.client.HttpClient;
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

  public GetIndex getUvIndex(String latitude, String longitude) {
    String urlTemplate = UriComponentsBuilder
        .fromUriString("https://api.openuv.io/api/v1/uv")
        .queryParam("lat", "{lat}")
        .queryParam("lng", "{lng}")
        .encode()
        .toUriString();

    Map<String, String> headers = new HashMap<>();
    headers.put("x-access-token", "openuv-4turllogbjxz-io");

    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("lat", latitude);
    uriVariables.put("lng", longitude);

    ResponseEntity<GetIndex> response = (ResponseEntity<GetIndex>) httpClient.getRequest(
        urlTemplate,
        headers,
        uriVariables,
        GetIndex.class);
    return response.getBody();
    //double uvIndex = response.getBody().getResult().getUv();
   //GetIndex.SafeExposureTime safeExposureTime = response.getBody().getResult().getSafeExposureTime();
    //return new UvIndexResponse(latitude, longitude, uvIndex , safeExposureTime);
  }
}
