package ar.edu.utn.dds.k3003.auxResource.fachadas;

import ar.edu.utn.dds.k3003.facades.FachadaHeladeras;
import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.EstadoViandaEnum;
import ar.edu.utn.dds.k3003.facades.dtos.ViandaDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FachadaViandaAux implements FachadaViandas {
  @Override
  public ViandaDTO modificarEstado(String s, EstadoViandaEnum estadoViandaEnum) throws NoSuchElementException {
    return new ViandaDTO(s, null, estadoViandaEnum, null, null);
  }

  @Override
  public ViandaDTO buscarXQR(String s) throws NoSuchElementException {
    return new ViandaDTO(s, null, null, null, null);
  }

  @Override
  public ViandaDTO agregar(ViandaDTO viandaDTO) {
    return null;
  }

  @Override
  public List<ViandaDTO> viandasDeColaborador(Long aLong, Integer integer, Integer integer1) throws NoSuchElementException {
    return null;
  }

  @Override
  public void setHeladerasProxy(FachadaHeladeras fachadaHeladeras) {

  }

  @Override
  public boolean evaluarVencimiento(String s) throws NoSuchElementException {
    return false;
  }

  @Override
  public ViandaDTO modificarHeladera(String s, int i) {
    return null;
  }
}
