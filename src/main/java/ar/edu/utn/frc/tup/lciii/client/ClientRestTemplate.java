package ar.edu.utn.frc.tup.lciii.client;

import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceGetXTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientRestTemplate {
    @Autowired
    private RestTemplate restTemplate;

    private final static String baseUrl="https://67a106a15bcfff4fabe171b0.mockapi.io/api/v1/device/device";

    public ResponseEntity<List<DeviceClient>> getDevicesXType() {
        try {
            DeviceClient[] devices = restTemplate.getForObject(baseUrl, DeviceClient[].class);
            return ResponseEntity.ok(Arrays.asList(devices));
        } catch (Exception e) {
            e.getStackTrace();
            throw e;
        }
    }
}
