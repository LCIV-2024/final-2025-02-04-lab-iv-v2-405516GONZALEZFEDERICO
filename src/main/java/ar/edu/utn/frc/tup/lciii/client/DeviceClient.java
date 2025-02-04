package ar.edu.utn.frc.tup.lciii.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceClient {
      private Integer id;
      private String hostNAme;
      private LocalDateTime createdDate;
      private String os;
      private String macAddress;
      private String Type;

}
