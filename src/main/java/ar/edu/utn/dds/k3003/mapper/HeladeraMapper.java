package ar.edu.utn.dds.k3003.mapper;

import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import ar.edu.utn.dds.k3003.model.Heladera;

import java.util.List;
import java.util.stream.Collectors;

public class HeladeraMapper {


  public HeladeraDTO originToDTO(Heladera heladera) {
    return new HeladeraDTO(Math.toIntExact(heladera.getId()), heladera.getNombre());
  }

  public Heladera DTOtoOrigin(HeladeraDTO heladeraDTO) {
    return new Heladera(Long.valueOf(heladeraDTO.getId()), heladeraDTO.getNombre());
  }

  public List<HeladeraDTO> originToListDTO(List<Heladera> heladeras) {

    return heladeras.stream().map(this::originToDTO).collect(Collectors.toList());
  }
}
