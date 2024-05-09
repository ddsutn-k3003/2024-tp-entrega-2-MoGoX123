package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.EstadoViandaEnum;
import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import ar.edu.utn.dds.k3003.facades.dtos.RetiroDTO;
import ar.edu.utn.dds.k3003.facades.dtos.TemperaturaDTO;
import ar.edu.utn.dds.k3003.facades.dtos.ViandaDTO;
import ar.edu.utn.dds.k3003.mapper.HeladeraMapper;
import ar.edu.utn.dds.k3003.mapper.TemperaturaMapper;
import ar.edu.utn.dds.k3003.model.Heladera;
import ar.edu.utn.dds.k3003.repository.RepoHeladera;
import lombok.Data;

import java.util.List;
import java.util.NoSuchElementException;

@Data
public class Fachada implements ar.edu.utn.dds.k3003.facades.FachadaHeladeras{

  private FachadaViandas fachadaViandas;
  private RepoHeladera repoHeladera;
  private HeladeraMapper heladeraMapper;
  private TemperaturaMapper temperaturaMapper;

  public Fachada() {
    this.repoHeladera = new RepoHeladera();
    this.heladeraMapper = new HeladeraMapper();
    this.temperaturaMapper = new TemperaturaMapper();
  }

  public Fachada(RepoHeladera repoHeladera, HeladeraMapper heladeraMapper, TemperaturaMapper temperaturaMapper) {
    this.repoHeladera = repoHeladera;
    this.heladeraMapper = heladeraMapper;
    this.temperaturaMapper = temperaturaMapper;
  }

  @Override
  public HeladeraDTO agregar(HeladeraDTO heladeraDTO) {

    // new Heladera(heladeraDTO.getNombre());
    Heladera heladera = this.heladeraMapper.DTOtoOrigin(heladeraDTO);
    heladera = this.repoHeladera.save(heladera);

    return this.heladeraMapper.originToDTO(heladera);
  }

  @Override
  public void depositar(Integer heladeraId, String qrVianda) throws NoSuchElementException {

    Heladera heladera = this.repoHeladera.findById(Long.valueOf(heladeraId));
    ViandaDTO viandaDTO = this.fachadaViandas.buscarXQR(qrVianda);
    this.fachadaViandas.modificarEstado(viandaDTO.getCodigoQR(), EstadoViandaEnum.DEPOSITADA);
    heladera.addVianda();
  }

  @Override
  public void retirar(RetiroDTO retiroDTO) throws NoSuchElementException {

    Heladera heladera = this.repoHeladera.findById(Long.valueOf(retiroDTO.getHeladeraId()));
    ViandaDTO viandaDTO = this.fachadaViandas.buscarXQR(retiroDTO.getQrVianda());

    this.fachadaViandas.modificarEstado(viandaDTO.getCodigoQR(), EstadoViandaEnum.RETIRADA);
    heladera.removeVianda();
  }

  @Override
  public Integer cantidadViandas(Integer heladeraId) throws NoSuchElementException {

    Heladera heladera = this.repoHeladera.findById(Long.valueOf(heladeraId));
    return heladera.getCantViandas();
  }

  @Override
  public void temperatura(TemperaturaDTO temperaturaDTO) {

    Heladera heladera = this.repoHeladera.findById(Long.valueOf(temperaturaDTO.getHeladeraId()));
    heladera.AgregarTemperatura(this.temperaturaMapper.DTOtoOrigin(temperaturaDTO));
  }

  @Override
  public List<TemperaturaDTO> obtenerTemperaturas(Integer heladeraId) {

    Heladera heladera = this.repoHeladera.findById(Long.valueOf(heladeraId));

    return this.temperaturaMapper.listOriginToListDTO(heladera.getRegistroTemperaturas(), heladera.getId());
  }

  @Override
  public void setViandasProxy(FachadaViandas fachadaViandas) {
    this.fachadaViandas = fachadaViandas;
  }
}
