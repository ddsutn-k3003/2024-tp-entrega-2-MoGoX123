package ar.edu.utn.dds.k3003;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.auxResource.DTOs.HeladeraDTOAux;
import ar.edu.utn.dds.k3003.auxResource.DTOs.RetiroDTOAux;
import ar.edu.utn.dds.k3003.auxResource.DTOs.TemperaturaDTOAux;
import ar.edu.utn.dds.k3003.auxResource.DTOs.ViandaDTOAux;
import ar.edu.utn.dds.k3003.auxResource.fachadas.FachadaViandaAux;
import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import io.javalin.Javalin;

import java.util.NoSuchElementException;

public class WebApp {

  public static void main(String[] args) {

    // Variables de entorno
    String port = System.getenv().getOrDefault("PORT", "8080");

    // Encendemos Javalin
    Javalin app = Javalin.create().start(Integer.parseInt(port));

    // Inicializamos la Fachada
    Fachada fachada = new Fachada();
    fachada.agregar(new HeladeraDTO(1, "Heladera campus"));

    // Este es solo para pruebas locales
    fachada.setViandasProxy(new FachadaViandaAux());

    // CONTROLLERS----------------------------------------------------------------------

    // Devolver Heladeras
    app.get("/heladeras", ctx -> {

      try {
        ctx.status(200).json(fachada.obtenerHeladeras());
      }
      catch (Exception e){
        ctx.status(500).result("Error interno");
      }
    });

    // Agregar Heladera
    app.post("/heladeras", ctx -> {

      try {
        fachada.agregar(ctx.bodyAsClass(HeladeraDTOAux.class).toDTO());
        ctx.status(200).result("Heladera agregada correctamente");
      }
      catch (Exception e){
        e.printStackTrace();
        ctx.status(400).result("Error de solicitud");
      }
    });

    // Obtener Heladera por ID
    app.get("/heladeras/{id}", ctx -> {

      try {
        Long id = Long.valueOf(ctx.pathParam("id"));

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
    });

    // Depositar vianda
    app.post("/depositos", ctx -> {

      try {
        ViandaDTOAux viandaDTOAux = ctx.bodyAsClass(ViandaDTOAux.class);
        fachada.depositar(Math.toIntExact(viandaDTOAux.getHeladeraId()), viandaDTOAux.getQrVianda());
        ctx.status(200).result("Vianda depositada correctamente");
      }
      catch (NoSuchElementException e){
        ctx.status(400).result("Error de solicitud");
      }
      catch (Exception e){
        e.printStackTrace();
        ctx.status(500).result("Error interno");
      }
    });

    // Retirar vianda
    app.post("/retiros", ctx -> {

      try {
        fachada.retirar(ctx.bodyAsClass(RetiroDTOAux.class).toDTO());
        ctx.status(200).result("Vianda retirada correctamente");
      }
      catch (NoSuchElementException e){
        ctx.status(400).result("Error de solicitud");
      }
      catch (Exception e){
        e.printStackTrace();
        ctx.status(500).result("Error interno");
      }
    });

    // Registrar temperatura
    app.post("/temperaturas", ctx -> {

      try {
        fachada.temperatura(ctx.bodyAsClass(TemperaturaDTOAux.class).toDto());
        ctx.status(200).result("Temperatura registrada correctamente");
      }
      catch (NoSuchElementException e){
        ctx.status(400).result("Error de solicitud");
      }
      catch (Exception e){
        e.printStackTrace();
        ctx.status(500).result("Error interno");
      }
    });

    // Registrar temperatura
    app.get("/heladeras/{heladeraId}/temperaturas", ctx -> {

      try {
        Integer id = Integer.valueOf(ctx.pathParam("heladeraId"));
        ctx.status(200).json(fachada.obtenerTemperaturas(id));
      }
      catch (NoSuchElementException e){
        ctx.status(400).result("Heladera no encontrada");
      }
      catch (Exception e){
        e.printStackTrace();
        ctx.status(500).result("Error interno");
      }
    });

  }

}

