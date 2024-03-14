package org.splitec.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpClient {

  private final RestTemplate restTemplate;

  @Autowired
  public HttpClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ResponseEntity<?> getRequest(String url,
                                           Map<String, String> headers,
                                           Map<String, String> uriVariables,
                                           Class<?> deserializer) {
    HttpHeaders httpHeaders = new HttpHeaders();
    headers.forEach(httpHeaders::set);
    HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
    return restTemplate.exchange(url, HttpMethod.GET, entity, deserializer, uriVariables);
  }

  public ResponseEntity<String> postRequest(String url, Map<String, String> headers, Object body) {
    HttpHeaders httpHeaders = new HttpHeaders();
    headers.forEach(httpHeaders::set);
    HttpEntity<Object> entity = new HttpEntity<>(body, httpHeaders);
    return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
  }

  public ResponseEntity<String> putRequest(String url, Map<String, String> headers, Object body) {
    HttpHeaders httpHeaders = new HttpHeaders();
    headers.forEach(httpHeaders::set);
    HttpEntity<Object> entity = new HttpEntity<>(body, httpHeaders);
    return restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
  }

  public ResponseEntity<String> deleteRequest(String url, Map<String, String> headers) {
    HttpHeaders httpHeaders = new HttpHeaders();
    headers.forEach(httpHeaders::set);
    HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
    return restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
  }
}
