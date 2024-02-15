package org.splitec.controller;

import org.splitec.client.HttpClient;
import org.splitec.dto.UvIndexResponse;
import org.splitec.service.UvService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UvController extends UvService {

  public UvController(HttpClient httpClient) {
    super(httpClient);
  }

  @GetMapping("/uv/index")
  public UvIndexResponse getIndex(@RequestParam double latitude,
                                  @RequestParam double longitude) {
    return getUvIndex(latitude, longitude);
  }
}
