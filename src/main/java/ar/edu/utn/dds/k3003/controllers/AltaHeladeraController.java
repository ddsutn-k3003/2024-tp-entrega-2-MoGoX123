package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.auxResource.DTOs.HeladeraDTOAux;
import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

public class AltaHeladeraController implements Handler {
  private final Fachada fachada;

  public AltaHeladeraController(Fachada fachada) {
    super();
    this.fachada = fachada;
  }

  @Override
  public void handle(@NotNull Context context) throws Exception {

    try{

      HeladeraDTOAux heladeraDTOAux = context.bodyAsClass(HeladeraDTOAux.class);

      this.fachada.agregar(new HeladeraDTO(heladeraDTOAux.getId(), heladeraDTOAux.getNombre()));
      context.status(200);
      context.result("Heladera agregada correctamente");
    }catch (NoSuchElementException e){

      context.status(400);
      context.result("Error de solicitud");
    }catch (Exception e){

      e.printStackTrace();
      context.status(500);
      context.result("Error interno");
    }

  }
}
