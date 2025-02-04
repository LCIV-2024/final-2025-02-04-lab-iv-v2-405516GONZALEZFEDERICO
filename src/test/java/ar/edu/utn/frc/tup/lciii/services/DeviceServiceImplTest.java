package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DeviceServiceImplTest {
    @MockBean
    private DeviceRepository deviceRepository;
    @SpyBean
    private DeviceService deviceService;
    @Test
    void postDevice() {
    }

    @Test
    void getDevicesPerType() {
    }

    @Test
    void getDevicesByThreshold() {
    }
}