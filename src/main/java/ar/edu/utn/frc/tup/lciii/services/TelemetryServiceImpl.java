package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.DeviceGetXTypeDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.TelemetryDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.TelemetryGetDto;
import ar.edu.utn.frc.tup.lciii.model.Device;
import ar.edu.utn.frc.tup.lciii.model.Telemetry;
import ar.edu.utn.frc.tup.lciii.repository.DeviceRepository;
import ar.edu.utn.frc.tup.lciii.repository.TelemetryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class TelemetryServiceImpl implements TelemetryService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TelemetryRepository telemetryRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public TelemetryDto postTelemetry(TelemetryDto telemetryDto) {

        if (telemetryDto==null){
            throw  new IllegalArgumentException("NO ingreso ninguna telemetria");
        }
        Optional<Device> device=this.deviceRepository.findById(telemetryDto.getHostname());
        if (device.isPresent()){
            Telemetry telemetryToSave=new Telemetry();
            telemetryToSave.setIp(telemetryDto.getIp());
            telemetryToSave.setHostname(telemetryDto.getHostname());
            telemetryToSave.setDevice(device.get());
            telemetryToSave.setDataDate(telemetryDto.getDataDate());
            telemetryToSave.setCpuUsage(telemetryDto.getCpuUsage());
            telemetryToSave.setHostDiskFree(telemetryDto.getHostDiskFree());
            telemetryToSave.setMicrophoneState(telemetryDto.getMicrophoneState());
            telemetryToSave.setScreenCaptureAllowed(telemetryDto.getScreenCaptureAllowed());
            telemetryToSave.setAudioCaptureAllowed(telemetryDto.getAudioCaptureAllowed());
            this.telemetryRepository.save(telemetryToSave);
            return this.modelMapper.map(telemetryToSave, TelemetryDto.class);
        }throw new IllegalArgumentException("no existe el device");

    }

    @Override
    public List<TelemetryGetDto> getTelemetriesRegistered() {
        List<Telemetry>getALlTelemetriesRegistered=this.telemetryRepository.findAll();
        if (getALlTelemetriesRegistered.isEmpty()){
            throw  new IllegalArgumentException("No hay telemetrias registradas");
        }else {
            Type telemetrias= new TypeToken<List<TelemetryGetDto>>(){}.getType();
            return modelMapper.map(getALlTelemetriesRegistered,telemetrias);
        }
    }


}
