package ar.edu.utn.dds.k3003.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegistroTemperatura {

  //Las temperaturas pueden estar en cualquier medida
  private LocalDateTime fechaMedicion;
  private long temperatura;

  public RegistroTemperatura(){
    super();
  }

}
