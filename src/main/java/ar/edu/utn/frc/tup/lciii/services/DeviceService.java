package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceGetXTypeDto;
import ar.edu.utn.frc.tup.lciii.enums.DeviceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {
    DeviceDto postDevice(DeviceDto deviceDto);
    List<DeviceGetXTypeDto> getDevicesPerType(DeviceType deviceType);

    List<DeviceGetXTypeDto> getDevicesByThreshold(Double lowThreshold, Double upThreshold);
    List<DeviceDto> saveConsumedDevices();
}

