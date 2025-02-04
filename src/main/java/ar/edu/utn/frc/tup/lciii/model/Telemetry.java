package ar.edu.utn.frc.tup.lciii.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "TELEMETRY")
@EqualsAndHashCode
public class Telemetry {

    @Id
    @SequenceGenerator(name = "telemetry_seq", sequenceName = "TELEMETRY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telemetry_seq")
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false)
    private String ip;

    @Column
    private String hostname;

    @Column(nullable = false)
    private LocalDateTime dataDate;

    @Column(nullable = false)
    private Double hostDiskFree;

    @Column(nullable = false)
    private Double cpuUsage;

    @Column(nullable = false)
    private String microphoneState;

    @Column(nullable = false)
    private Boolean screenCaptureAllowed;

    @Column(nullable = false)
    private Boolean audioCaptureAllowed;



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOSTNAME", referencedColumnName = "HOSTNAME", insertable = false, updatable = false)
    private Device device;

    @PrePersist
    protected void onCreate() {
        dataDate = LocalDateTime.now();
    }
}
