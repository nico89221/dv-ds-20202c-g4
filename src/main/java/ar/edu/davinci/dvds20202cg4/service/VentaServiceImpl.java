package ar.edu.davinci.dvds20202cg4.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20202cg4.model.Cliente;
import ar.edu.davinci.dvds20202cg4.model.Item;
import ar.edu.davinci.dvds20202cg4.model.Prenda;
import ar.edu.davinci.dvds20202cg4.model.Venta;
import ar.edu.davinci.dvds20202cg4.model.VentaEfectivo;
import ar.edu.davinci.dvds20202cg4.model.VentaTarjeta;
import ar.edu.davinci.dvds20202cg4.repository.VentaEfectivoRepository;
import ar.edu.davinci.dvds20202cg4.repository.VentaRepository;
import ar.edu.davinci.dvds20202cg4.repository.VentaTarjetaRepository;

@Service
public class VentaServiceImpl implements VentaService {
    
    private final Logger LOGGER = LoggerFactory.getLogger(VentaServiceImpl.class);

    private final VentaRepository ventaRepository;
    private final VentaEfectivoRepository ventaEfectivoRepository;
    private final VentaTarjetaRepository ventaTarjetaRepository;
    
    private final ClienteService clienteService;

    private final PrendaService prendaService;
    
    private final ItemService itemService;

    
    @Autowired
    public VentaServiceImpl(final VentaRepository ventaRepository,
            final VentaEfectivoRepository ventaEfectivoRepository,
            final VentaTarjetaRepository ventaTarjetaRepository,
            final ClienteService clienteService,
            final PrendaService prendaService,
            final ItemService itemService) {
        this.ventaRepository = ventaRepository;
        this.ventaEfectivoRepository = ventaEfectivoRepository;
        this.ventaTarjetaRepository = ventaTarjetaRepository;
        this.clienteService = clienteService;
        this.prendaService = prendaService;
        this.itemService = itemService;
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
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }
    

    @Override
    public VentaEfectivo save(VentaEfectivo venta) throws Exception {

        Cliente cliente = null;
        if (venta.getCliente().getId() != null) {
            cliente = getCliente(venta.getCliente().getId()); 
        } else {
            throw new Exception("El cliente es obligatorio");
        }

        List<Item> items = new ArrayList<Item>(); 
        if (venta.getItems() != null) {
            items = getItems(venta.getItems());
        }
        
        venta = VentaEfectivo.builder()
                .cliente(cliente)
                .fecha(Calendar.getInstance().getTime())
                .items(items)
                .build();
        
        return ventaEfectivoRepository.save(venta);
    }

    @Override
    public VentaEfectivo save(VentaEfectivo ventaEfectivo, Item item) throws Exception {

        ventaEfectivo.addItem(item);
        
        return ventaEfectivoRepository.save(ventaEfectivo);
    }

    @Override
    public VentaTarjeta save(VentaTarjeta venta) throws Exception {
        Cliente cliente = null;
        if (venta.getCliente().getId() != null) {
            cliente = getCliente(venta.getCliente().getId()); 
        } else {
            throw new Exception("El cliente es obligatorio");
        }

        List<Item> items = new ArrayList<Item>(); 
        if (venta.getItems() != null) {
            items = getItems(venta.getItems());
        }
        
        venta = VentaTarjeta.builder()
                .cliente(cliente)
                .fecha(Calendar.getInstance().getTime())
                .items(items)
                .coeficienteTarjeta(new BigDecimal(0.01D))
                .build();
        return ventaTarjetaRepository.save(venta);
    }

    @Override
    public VentaTarjeta save(VentaTarjeta ventaTarjeta, Item item) throws Exception {

        ventaTarjeta.addItem(item);
        
        return ventaTarjetaRepository.save(ventaTarjeta);
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

    @Override
    public Venta addItem(Long id, Item item) throws Exception {
        
        Venta venta = null;
        
        Optional<Venta> ventaOptional = ventaRepository.findById(id);        
        if (ventaOptional.isPresent()) {
            venta = ventaOptional.get();
        } else {
            throw new Exception("Venta no existe");
        }
        
        Prenda prenda = getPrenda(item);
        Item newItem = Item.builder()
                .cantidad(item.getCantidad())
                .prenda(prenda)
                .venta(venta)
                .build();
        
        newItem = itemService.save(newItem);
        
        venta.addItem(newItem);
                
        return venta;
    }

    @Override
    public Venta updateItem(Long ventaId, Long itemId, Item item) throws Exception {
        
        Venta venta = getVenta(ventaId);
        
        Item actualItem = getItem(itemId);
        
        actualItem.setCantidad(item.getCantidad());
        
        actualItem = itemService.save(actualItem);
        
        return venta;
    }
    
    @Override
    public Venta deleteItem(Long ventaId, Long itemId) throws Exception {
        Venta venta = getVenta(ventaId);
        
        Item actualItem = getItem(itemId);
        
        itemService.delete(itemId);
        
        venta.getItems().remove(actualItem);
        
        ventaRepository.save(venta);
        
        return venta;
    }


    private List<Item> getItems(List<Item> requestItems) throws Exception {
        List<Item> items = new ArrayList<Item>();
        for (Item requestItem : requestItems) {
            
            Prenda prenda = getPrenda(requestItem);
            Item item = Item.builder()
                    .cantidad(requestItem.getCantidad())
                    .prenda(prenda)
                    .build();
            items.add(item);
        }
        return items;
    }

    private Prenda getPrenda(Item requestItem) throws Exception {
        Optional<Prenda> prendaOptional = null; 
        if (requestItem.getPrenda().getId() != null) {
            prendaOptional = prendaService.findById(requestItem.getPrenda().getId());
            if (prendaOptional.isPresent()) {
                return prendaOptional.get();
            } else {
                throw new Exception("Prenda no encotrada");
            }
        } else {
            throw new Exception("La Prenda es obligatoria");
        }
    }

    private Venta getVenta(Long id) throws Exception {
        Optional<Venta> ventaOptional = ventaRepository.findById(id);
        if (ventaOptional.isPresent()) {
            return ventaOptional.get();
        } else {
            throw new Exception("Venta no encotrado");
        }
    }

    private Item getItem(Long id) throws Exception {
        Optional<Item> itemOptional = itemService.findById(id);
        if (itemOptional.isPresent()) {
            return itemOptional.get();
        } else {
            throw new Exception("Item no encotrado");
        }
    }
    
    private Cliente getCliente(Long id) throws Exception {
        Optional<Cliente> itemOptional = clienteService.findById(id);
        if (itemOptional.isPresent()) {
            return itemOptional.get();
        } else {
            throw new Exception("Cliente no encotrado");
        }
    }

}

