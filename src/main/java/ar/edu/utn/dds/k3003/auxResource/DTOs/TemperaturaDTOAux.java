package ar.edu.utn.dds.k3003.auxResource.DTOs;

import ar.edu.utn.dds.k3003.facades.dtos.TemperaturaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperaturaDTOAux {

  private Integer temperatura;
  private Integer heladeraId;
  private LocalDateTime fechaMedicion;

  public TemperaturaDTO toDto(){
    return new TemperaturaDTO(temperatura, heladeraId, fechaMedicion);
  }

}
