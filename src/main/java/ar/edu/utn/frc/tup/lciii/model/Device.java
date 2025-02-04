package ar.edu.utn.frc.tup.lciii.model;

import ar.edu.utn.frc.tup.lciii.enums.DeviceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "DEVICE")
public class Device {

    @Id
    @Column(name = "HOSTNAME", unique = true)
    private String hostName;

    @OneToOne(mappedBy = "device")
    private Telemetry telemetry;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private String os;

    @Column(unique = true, nullable = false)
    private String macAddress;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }
}
