package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.TelemetryDto;
import ar.edu.utn.frc.tup.lciii.services.TelemetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/telemetry")
public class TelemetryController {
    private final TelemetryService telemetryService;
    @PostMapping
    public ResponseEntity<TelemetryDto> createTelemetry(@RequestBody TelemetryDto telemetryDto) {
        return new ResponseEntity<>(telemetryService.postTelemetry(telemetryDto), HttpStatus.CREATED);
    }

}