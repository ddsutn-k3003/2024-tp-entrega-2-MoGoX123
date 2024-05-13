package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.auxResource.DTOs.HeladeraDTOAux;
import ar.edu.utn.dds.k3003.auxResource.DTOs.RetiroDTOAux;
import ar.edu.utn.dds.k3003.auxResource.DTOs.TemperaturaDTOAux;
import ar.edu.utn.dds.k3003.auxResource.DTOs.ViandaDTOAux;
import ar.edu.utn.dds.k3003.auxResource.fachadas.FachadaViandaAux;
import ar.edu.utn.dds.k3003.controllers.HeladeraController;
import ar.edu.utn.dds.k3003.controllers.TemperaturaController;
import ar.edu.utn.dds.k3003.controllers.ViandaController;
import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import io.javalin.Javalin;

import java.util.NoSuchElementException;

public class WebApp {

  private static final Fachada fachada = new Fachada();
  private static final HeladeraController heladeraController = new HeladeraController(fachada);
  private static final ViandaController viandaController = new ViandaController(fachada);
  private static final TemperaturaController temperaturaController = new TemperaturaController(fachada);

  public static void main(String[] args) {

    // Variables de entorno
    String URL_VIANDAS = System.getenv().get("URL_VIANDAS");
    String URL_LOGISTICA = System.getenv().get("URL_LOGISTICA");
    String URL_HELADERAS = System.getenv().get("URL_HELADERAS");
    String URL_COLABORADORES = System.getenv().get("URL_COLABORADORES");
    String port = System.getenv().getOrDefault("PORT", "8080");

    // Encendemos Javalin
    Javalin app = Javalin.create().start(Integer.parseInt(port));

    // Este es solo para pruebas locales
      fachada.setViandasProxy(new FachadaViandaAux());
      fachada.agregar(new HeladeraDTO(1, "heladera prueba"));
    // Este es solo para pruebas locales

    // LLAMADAS-------------------------------------------------------------------------------------

    // Devolver Heladeras
    app.get("/heladeras", heladeraController::getHeladeras);

    // Agregar Heladera
    app.post("/heladeras", heladeraController::addHeladera);

    // Obtener Heladera por ID
    app.get("/heladeras/{id}", heladeraController::getHeladeraId);

    // Depositar vianda
    app.post("/depositos", viandaController::addVianda);

    // Retirar vianda
    app.post("/retiros", viandaController::deleteVianda);

    // Registrar temperatura
    app.post("/temperaturas", temperaturaController::addTemperatura);

    // Registrar temperatura
    app.get("/heladeras/{heladeraId}/temperaturas", temperaturaController::allTemperatura);

  }

}

