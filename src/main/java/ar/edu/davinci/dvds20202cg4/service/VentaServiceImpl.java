package ar.edu.davinci.dvds20202cg4.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20202cg4.model.Venta;
import ar.edu.davinci.dvds20202cg4.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService {
    
    private final Logger LOGGER = LoggerFactory.getLogger(VentaServiceImpl.class);

    private final VentaRepository ventaRepository;
    
    @Autowired
    public VentaServiceImpl(final VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> listAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Page<Venta> list(Pageable pageable) {
        LOGGER.info("Pagegable: offset: " + pageable.getOffset() + " - pageSize:" + pageable.getPageSize());
        return ventaRepository.findAll(pageable);
    }

    @Override
    public Venta findById(Long id) {
        Optional<Venta> ventaOptional = ventaRepository.findById(id);
        if (ventaOptional.isPresent()) {
            return ventaOptional.get();
        }
        return null;
    }

    @Override
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void delete(Venta venta) {
        ventaRepository.delete(venta);
    }

    @Override
    public void delete(Long id) {
        ventaRepository.deleteById(id);
    }
    
    @Override
    public long count() {
        return ventaRepository.count();
    }

}
