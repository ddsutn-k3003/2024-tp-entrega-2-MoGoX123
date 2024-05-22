package ar.edu.utn.dds.k3003.repository;

import ar.edu.utn.dds.k3003.model.Heladera;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class RepoHeladera {
  private final Collection<Heladera> heladeras = new ArrayList<>();
  private static AtomicLong seqId = new AtomicLong();

  public Heladera save(Heladera heladera) {

    heladera.setId(seqId.getAndIncrement());
    this.heladeras.add(heladera);
    return heladera;
  }

  public Heladera findById(Long heladeraId) throws NoSuchElementException {

    Optional<Heladera> heladera = this.heladeras.stream()
        .filter(heladera1 -> heladera1.getId().equals(heladeraId))
        .findFirst();

    return  heladera.orElseThrow(() -> new NoSuchElementException("No se encuentra referencia a la heladera con id " + heladeraId));
  }

  public int size() {
    return heladeras.size();
  }

  public List<Heladera> getAll() {
    return (List<Heladera>) this.heladeras;
  }
}
