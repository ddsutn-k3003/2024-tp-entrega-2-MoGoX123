package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.app.Fachada;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class ObtenerHeladerasController implements Handler {
  private final Fachada fachada;

  public ObtenerHeladerasController(Fachada fachada) {
    super();
    this.fachada = fachada;
  }

  @Override
  public void handle(@NotNull Context context) throws Exception {

    context.status(200);
    context.json(this.fachada.obtenerHeladeras());
  }
}
