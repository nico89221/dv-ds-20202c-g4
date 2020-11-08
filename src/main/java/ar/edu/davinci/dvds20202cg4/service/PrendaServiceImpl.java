package ar.edu.davinci.dvds20202cg4.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import ar.edu.davinci.dvds20202cg4.model.Prenda;
import ar.edu.davinci.dvds20202cg4.repository.PrendaRepository;



@Service
public class PrendaServiceImpl implements PrendaService {

	private final Logger LOGGER = LoggerFactory.getLogger(PrendaServiceImpl.class);

	
	private final PrendaRepository prendaRepository;
	
	
	@Autowired
    public PrendaServiceImpl(final PrendaRepository prendaRepository) {
        this.prendaRepository = prendaRepository;
    }

    @Override
    public List<Prenda> listAll() {
        return prendaRepository.findAll();
    }

	

    @Override
    public Prenda findById(Long id) {
        Optional<Prenda> prendaOptional = prendaRepository.findById(id);
        if (prendaOptional.isPresent()) {
            return prendaOptional.get();
        }
        return null;
    }

    @Override
    public Prenda save(Prenda prenda) {
        // TODO Auto-generated method stub
        return prendaRepository.save(prenda);
    }


	@Override
    public void delete(Prenda prenda) {
        prendaRepository.delete(prenda);

	}

	
	// revisar este metodo
	

	@Override
	public Page<Prenda> list(org.springframework.data.domain.Pageable pageable){
		LOGGER.info("Pagegable: offset: " + ((Pageable) pageable).getOffset() + " - pageSize:" + ((Pageable) pageable).getPageSize());
        return prendaRepository.findAll(pageable);
		
		
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return prendaRepository.count();
	}
	

}