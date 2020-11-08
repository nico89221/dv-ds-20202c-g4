package ar.edu.davinci.dvds20202cg4.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import ar.edu.davinci.dvds20202cg4.model.Prenda;


public interface PrendaService {

	public List<Prenda> listAll();
	public Page<Prenda> list(org.springframework.data.domain.Pageable pageable);
	public Prenda findById(Long id);
	public Prenda save(Prenda prenda);
	public void delete(Prenda prenda);

	
	
}
