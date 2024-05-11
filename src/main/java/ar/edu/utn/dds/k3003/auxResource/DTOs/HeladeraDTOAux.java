package ar.edu.utn.dds.k3003.auxResource.DTOs;

import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeladeraDTOAux {

  private Integer id;
  private String nombre;
  private Integer cantidadDeViandas;

  public HeladeraDTOAux(HeladeraDTO dto) {
    this.id = dto.getId();
    this.nombre = dto.getNombre();
    this.cantidadDeViandas = 0;
  }

  public HeladeraDTO toDTO() {
    return new HeladeraDTO(this.id, this.nombre);
  }
}
