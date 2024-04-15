package org.splitec.controller;

import org.splitec.client.HttpClient;
import org.splitec.dto.ErrorResponse;
import org.splitec.dto.UvExposureInfoRequest;
import org.splitec.dto.UvExposureInfoResponse;
import org.splitec.dto.UvHealthPointsResponse;
import org.splitec.model.DailyPoints;
import org.splitec.service.JwtService;
import org.splitec.service.UvHealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UvHealthController extends UvHealthService {

  JwtService tokenService = new JwtService();

  public UvHealthController(HttpClient httpClient) {
    super(httpClient);
  }

  @GetMapping("/uv/dailypoints")
  public ResponseEntity<Object> getDailyPoints(@RequestHeader String authorization) {
    try {
      String username = tokenService.validateTokenAndRetrieveSubject(authorization.replace("Bearer ", ""));
      List<DailyPoints> healthPoints = findUserHealthPoints(username);
      return ResponseEntity.ok(new UvHealthPointsResponse(healthPoints, "Success"));
    } catch (Exception e) {
      return ResponseEntity.status(404).body(new ErrorResponse(e.getLocalizedMessage(), 404));
    }
  }

  @PostMapping("/uv/dailypoints/insert")
  public ResponseEntity<Object> insertHealthPoints(@RequestHeader String authorization,
                                                   @RequestBody UvExposureInfoRequest exposureInfoRequest) {
    try {
      String username = tokenService.validateTokenAndRetrieveSubject(authorization.replace("Bearer ", ""));
      insertDailyPointRegister(username, exposureInfoRequest.getDailyPoints());
      return ResponseEntity.ok(new UvHealthPointsResponse("Success!"));
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
