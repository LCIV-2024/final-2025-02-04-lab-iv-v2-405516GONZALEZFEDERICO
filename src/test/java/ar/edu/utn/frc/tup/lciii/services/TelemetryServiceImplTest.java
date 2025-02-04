package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.repository.TelemetryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TelemetryServiceImplTest {
    @MockBean
    private TelemetryRepository telemetryRepository;
    @SpyBean
    private TelemetryService telemetryService;
    @MockBean
    private DeviceService deviceService;
    @Test
    void postTelemetry() {
    }

    @Test
    void getTelemetriesRegistered() {
    }
}