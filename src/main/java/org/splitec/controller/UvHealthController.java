package org.splitec.controller;

import org.splitec.client.HttpClient;
import org.splitec.dto.UvExposureInfoRequest;
import org.splitec.dto.UvExposureInfoResponse;
import org.splitec.dto.UvHealthPointsResponse;
import org.splitec.service.JwtService;
import org.splitec.service.UvHealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UvHealthController extends UvHealthService {

  JwtService tokenService = new JwtService();
  public UvHealthController(HttpClient httpClient) {
    super(httpClient);
  }

  @GetMapping("/uv/health")
  public UvHealthPointsResponse getHealthPoints(@RequestHeader String authorization) {
    String username = tokenService.validateTokenAndRetrieveSubject(authorization.replace("Bearer ", ""));
    int healthPoints = findUserHealthPoints(username);
    return new UvHealthPointsResponse(healthPoints);
  }

  @PostMapping("/uv/health/highuv")
  public UvExposureInfoResponse possibleUvExposure(@RequestBody UvExposureInfoRequest expoInfo,
                                                   @RequestHeader String authorization) {
    String username = tokenService.validateTokenAndRetrieveSubject(authorization.replace("Bearer ", ""));
    return userUvExposed(expoInfo, username);
  }

}
