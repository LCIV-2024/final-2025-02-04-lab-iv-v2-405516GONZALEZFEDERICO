package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.client.ClientRestTemplate;
import ar.edu.utn.frc.tup.lciii.client.DeviceClient;
import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceGetXTypeDto;
import ar.edu.utn.frc.tup.lciii.enums.DeviceType;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ClientRestTemplate restTemplate;

    @Override
    public DeviceDto postDevice(DeviceDto deviceDto) {
        if (deviceDto==null){
            throw  new IllegalArgumentException("nO ingreso un dispositivo");
        }
        Optional<Device> device=this.deviceRepository.findById(deviceDto.getHostname());
        if (device.isPresent()){
            throw  new IllegalArgumentException("ya existe el hostname del dispositivo");

        }

        Device  deviceToSave=this.modelMapper.map(deviceDto,Device.class);
        this.deviceRepository.save(deviceToSave);
        return this.modelMapper.map(deviceToSave,DeviceDto.class);
    }

    @Override
    public List<DeviceGetXTypeDto> getDevicesPerType(DeviceType deviceType) {
        List<Device>getAllDevicesPerType=this.deviceRepository.getAllByType(deviceType);
        if (getAllDevicesPerType.isEmpty()){
            throw  new IllegalArgumentException("No hay dispositivos de ese tipo");
        }else {
            for (Device device:getAllDevicesPerType){
                DeviceGetXTypeDto deviceGetXTypeDto=new DeviceGetXTypeDto();
                deviceGetXTypeDto.setType(deviceType);
                deviceGetXTypeDto.setOs(device.getOs());
                deviceGetXTypeDto.setMacAddres(device.getMacAddress());
                deviceGetXTypeDto.setId(device.getTelemetry().getId());
            }
            Type devices= new TypeToken<List<DeviceGetXTypeDto>>(){}.getType();
            return modelMapper.map(getAllDevicesPerType,devices);
        }
    }

    @Override
    public List<DeviceGetXTypeDto> getDevicesByThreshold(Double lowThreshold, Double upThreshold) {
        List<Device> devices = deviceRepository.findDevicesByTelemetryCpuBetween(lowThreshold, upThreshold);

        return devices.stream()
                .map(device -> modelMapper.map(device, DeviceGetXTypeDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<DeviceDto> saveConsumedDevices() {
        ResponseEntity<List<DeviceClient>> response = restTemplate.getDevicesXType();
        if (!response.getBody().isEmpty()) {
            List<DeviceClient> allDevices = response.getBody();

            int devicesToSave = allDevices.size() / 2;

            List<DeviceClient> selectedDevices = new ArrayList<>(allDevices);
            java.util.Collections.shuffle(selectedDevices);
            selectedDevices = selectedDevices.subList(0, devicesToSave);

            List<Device> devices = selectedDevices.stream()
                    .map(deviceDto -> {
                        Device device = modelMapper.map(deviceDto, Device.class);
                        device.setCreatedDate(java.time.LocalDateTime.now());
                        device.setHostName(null);
                        return device;
                    })
                    .collect(Collectors.toList());

            List<Device> savedDevices = deviceRepository.saveAll(devices);

            Type listType = new TypeToken<List<DeviceDto>>(){}.getType();
            return modelMapper.map(savedDevices, listType);
        }
        return new ArrayList<>();
    }

}
