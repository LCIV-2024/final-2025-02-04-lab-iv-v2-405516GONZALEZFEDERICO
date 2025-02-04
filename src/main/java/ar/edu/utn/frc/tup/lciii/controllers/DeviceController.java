package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceGetXTypeDto;
import ar.edu.utn.frc.tup.lciii.enums.DeviceType;
import ar.edu.utn.frc.tup.lciii.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @PostMapping("/api/device")
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto requestDTO) {
        return new ResponseEntity<>(deviceService.postDevice(requestDTO), HttpStatus.CREATED);
    }


    @GetMapping("/api/device")
    public ResponseEntity<?> getDevices(
            @RequestParam(required = false) DeviceType type,
            @RequestParam(required = false) Double lowThreshold,
            @RequestParam(required = false) Double upThreshold) {

        if (lowThreshold != null && upThreshold != null) {
            if (lowThreshold > upThreshold) {
                return ResponseEntity.badRequest()
                        .body("El umbral inferior no puede ser mayor al umbral superior");
            }
            return ResponseEntity.ok(deviceService.getDevicesByThreshold(lowThreshold, upThreshold));
        }

        if (type != null) {
            return ResponseEntity.ok(deviceService.getDevicesPerType(type));
        }


        return ResponseEntity.badRequest()
                .body("Debe especificar type o thresholds como parámetros de búsqueda");
    }


    @PostMapping("/save-consumed-devices")
    public ResponseEntity<List<DeviceDto>> saveConsumedDevices() {
        try {
            List<DeviceDto> savedDevices = deviceService.saveConsumedDevices();
            return ResponseEntity.ok(savedDevices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}