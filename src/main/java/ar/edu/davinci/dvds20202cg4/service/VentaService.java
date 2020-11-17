package ar.edu.davinci.dvds20202cg4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.davinci.dvds20202cg4.model.Venta;

public interface VentaService {
    
    public List<Venta> listAll();
    public Page<Venta> list(Pageable pageable);
    public Venta findById(Long id);
    public Venta save(Venta venta);
    public void delete(Venta venta);
    public void delete(Long id);
    public long count();
}

