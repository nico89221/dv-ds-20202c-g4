package ar.edu.davinci.dvds20202cg4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.davinci.dvds20202cg4.model.Cliente;

public interface ClienteService {
    
    public List<Cliente> listAll();
    public Page<Cliente> list(Pageable pageable);
    public Optional<Cliente> findById(Long id);
    public Cliente save(Cliente cliente);
    public void delete(Cliente cliente);
    public void delete(Long id);
    public long count();
}
