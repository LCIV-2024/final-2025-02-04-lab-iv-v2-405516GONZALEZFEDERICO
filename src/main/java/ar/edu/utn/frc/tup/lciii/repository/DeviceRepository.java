package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.enums.DeviceType;
import ar.edu.utn.frc.tup.lciii.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,String> {
    List<Device>getAllByType(DeviceType deviceType);

    @Query("SELECT d FROM Device d JOIN d.telemetry t WHERE t.cpuUsage BETWEEN :lowThreshold AND :upThreshold")
    List<Device> findDevicesByTelemetryCpuBetween(
            @Param("lowThreshold") Double lowThreshold,
            @Param("upThreshold") Double upThreshold);
}
