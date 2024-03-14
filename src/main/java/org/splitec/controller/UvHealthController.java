package org.splitec.controller;

import org.splitec.client.HttpClient;
import org.splitec.dto.ErrorResponse;
import org.splitec.dto.UvExposureInfoRequest;
import org.splitec.dto.UvExposureInfoResponse;
import org.splitec.dto.UvHealthPointsResponse;
import org.splitec.service.JwtService;
import org.splitec.service.UvHealthService;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Object> getHealthPoints(@RequestHeader String authorization) {
    try {
      String username = tokenService.validateTokenAndRetrieveSubject(authorization.replace("Bearer ", ""));
      int healthPoints = findUserHealthPoints(username);
      return ResponseEntity.ok(new UvHealthPointsResponse(healthPoints));
    } catch (Exception e) {
      return ResponseEntity.status(404).body(new ErrorResponse(e.getLocalizedMessage(), 404));
    }
  }

  @PostMapping("/uv/health/highuv")
  public ResponseEntity<Object> possibleUvExposure(@RequestBody UvExposureInfoRequest expoInfo,
                                                   @RequestHeader String authorization) {
    try {
      String username = tokenService.validateTokenAndRetrieveSubject(authorization.replace("Bearer ", ""));
      UvExposureInfoResponse response = userUvExposed(expoInfo, username);
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      return ResponseEntity.unprocessableEntity().body(new ErrorResponse(e.getLocalizedMessage(), 422));
    }

  }

}
