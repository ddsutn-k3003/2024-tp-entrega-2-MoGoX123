package ar.edu.utn.dds.k3003.mapper;

import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import ar.edu.utn.dds.k3003.model.Heladera;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HeladeraMapper {


  public HeladeraDTO originToDTO(Heladera heladera) {
    return new HeladeraDTO(Math.toIntExact(heladera.getId()), heladera.getNombre(), heladera.getCantViandas());
  }

  public Heladera DTOtoOrigin(HeladeraDTO heladeraDTO) {

    Heladera heladera;

    if (Objects.isNull(heladeraDTO.getId())) {
      heladera = new Heladera(null, heladeraDTO.getNombre());
    }else {
      heladera = new Heladera(Long.valueOf(heladeraDTO.getId()), heladeraDTO.getNombre());
    }
    return heladera;
  }

  public List<HeladeraDTO> originToListDTO(List<Heladera> heladeras) {

    return heladeras.stream().map(this::originToDTO).collect(Collectors.toList());
  }
}
