package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

public class ObtenerHeladeraIdController implements Handler {

  private final Fachada fachada;
  private final Long id;

  public ObtenerHeladeraIdController(Fachada fachada, Long id) {
    super();
    this.fachada = fachada;
    this.id = id;
  }

  @Override
  public void handle(@NotNull Context context) throws Exception {

    try{

      HeladeraDTO heladeraDTO = this.fachada.obtenerHeladeraPorId(this.id);
      context.status(200);
      context.json(heladeraDTO);
    }catch (NoSuchElementException e){

      context.status(400);
      context.result("Heladera no encontrada");
    }catch (Exception e){

      context.status(500);
      context.result("Error interno");
    }

  }
}
