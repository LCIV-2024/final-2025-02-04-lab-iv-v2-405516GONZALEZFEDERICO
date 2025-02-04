package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.TelemetryDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.TelemetryGetDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TelemetryService {
    TelemetryDto postTelemetry(TelemetryDto telemetryDto);
    List<TelemetryGetDto> getTelemetriesRegistered();
}
