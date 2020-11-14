package ar.edu.davinci.dvds20202cg4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.davinci.dvds20202cg4.model.VentaEfectivo;

@Repository
public interface VentaRepository extends JpaRepository<VentaEfectivo, Long> {

}
