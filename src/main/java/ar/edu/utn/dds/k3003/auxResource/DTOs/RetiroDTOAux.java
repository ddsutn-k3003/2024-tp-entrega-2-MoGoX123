package ar.edu.utn.dds.k3003.auxResource.DTOs;

import ar.edu.utn.dds.k3003.facades.dtos.RetiroDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetiroDTOAux {

  private Long id;
  private String qrVianda;
  private String tarjeta;
  private LocalDateTime fechaRetiro;
  private Integer heladeraId;

  public RetiroDTO toDTO() {
    return new RetiroDTO(qrVianda, tarjeta, fechaRetiro, heladeraId);
  }

}
