package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.auxResource.DTOs.HeladeraDTOAux;
import ar.edu.utn.dds.k3003.auxResource.DTOs.ViandaDTOAux;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

public class HeladeraController {


  private final Fachada fachada;

  public HeladeraController(Fachada fachada) {
    this.fachada = fachada;
  }

  public void getHeladeras(@NotNull Context ctx) {

    try {
      ctx.status(200).json(fachada.obtenerHeladeras());
    }
    catch (Exception e){
      ctx.status(500).result("Error interno");
    }

  }

  public void addHeladera(@NotNull Context ctx) {

    try {
      fachada.agregar(ctx.bodyAsClass(HeladeraDTOAux.class).toDTO());
      ctx.status(200).result("Heladera agregada correctamente");
    }
    catch (Exception e){
      e.printStackTrace();
      ctx.status(400).result("Error de solicitud");
    }

  }

  public void getHeladeraId(@NotNull Context ctx) {

    try {
      long id = Long.parseLong(ctx.pathParam("id"));

      HeladeraDTOAux heladeraDTO = new HeladeraDTOAux(fachada.obtenerHeladeraPorId(id));
      heladeraDTO.setCantidadDeViandas(fachada.cantidadViandas(Math.toIntExact(id)));

      ctx.status(200).json(heladeraDTO);
    }
    catch (NoSuchElementException e){
      ctx.status(404).result("Heladera no encontrada");
    }
    catch (Exception e){
      e.printStackTrace();
      ctx.status(500).result("Error interno");
    }

  }
}
