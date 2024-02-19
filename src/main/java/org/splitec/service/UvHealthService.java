package org.splitec.service;

import org.splitec.client.HttpClient;
import org.splitec.dto.UvExposureInfoRequest;
import org.splitec.dto.UvExposureInfoResponse;
import org.splitec.model.GetIndex;
import org.springframework.stereotype.Service;

@Service
public class UvHealthService extends UvService {

  public UvHealthService(HttpClient httpClient) {
    super(httpClient);
  }

  public UvExposureInfoResponse userUvExposed(UvExposureInfoRequest expoInfo, String username) {
    UvExposureInfoResponse expoResponse = new UvExposureInfoResponse();
    if (isUvExposed(expoInfo.getRssiWifi(), expoInfo.getGpsPrecision(), expoInfo.getLuxValue())) {
      GetIndex response = getUvIndex(expoInfo.getLat(), expoInfo.getLon());

      expoResponse.setMaxExposureTime(findUserSkinType(response.getResult().getSafeExposureTime(), username));

    }
    return expoResponse;
  }

  public int findUserSkinType(GetIndex.SafeExposureTime exposureTimeResponse, String username) {
    // TODO: Adicionar uma lógica para recuperar o skinType no banco de dados com base no username
    int skinType = 1;
    switch (skinType) {
      case 1:
        return exposureTimeResponse.getSt1();
      case 2:
        return exposureTimeResponse.getSt2();
      case 3:
        return exposureTimeResponse.getSt3();
      case 4:
        return exposureTimeResponse.getSt4();
      case 5:
        return exposureTimeResponse.getSt5();
      case 6:
        return exposureTimeResponse.getSt6();
      default:
        return 0;
    }
  }

  public int findUserHealthPoints(String username) {
    //TODO: Fazer uma busca no banco de dados e recuperar o valor de UVHealthPoints baseado no username
    return 98;
  }

  public static boolean isUvExposed(double rssiWifi, double gpsPrecision, double luxValue) {
    double scoreWifi = (Math.min(Math.max(rssiWifi, -90), -30) + 90) / 60;

    double scoreGps;
    if (gpsPrecision <= 100) {
      scoreGps = 1;
    } else {
      scoreGps = 0;
    }

    double scoreLux;
    if (luxValue <= 100) {
      scoreLux = 0;
    } else {
      scoreLux = 1;
    }

    double scoreTotal = (0.1 * scoreWifi) + (0.3 * scoreGps) + (0.8 * scoreLux);
    return scoreTotal > 1;
  }
}