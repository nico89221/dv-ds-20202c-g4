package ar.edu.davinci.dvds20202cg4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.davinci.dvds20202cg4.controller.rest.request.PrendaInsertRequest;
import ar.edu.davinci.dvds20202cg4.controller.rest.request.PrendaUpdateRequest;
import ar.edu.davinci.dvds20202cg4.controller.rest.response.PrendaResponse;
import ar.edu.davinci.dvds20202cg4.model.Prenda;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class OrikaConfiguration {
    
    private final Logger LOGGER = LoggerFactory.getLogger(OrikaConfiguration.class);

    private final ObjectMapper objectMapper;
    
    public OrikaConfiguration() {
        objectMapper = new ObjectMapper();
    }
    
    @Bean
    public MapperFacade mapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Prenda.class, PrendaInsertRequest.class).byDefault().register();
        mapperFactory.classMap(Prenda.class, PrendaUpdateRequest.class).byDefault().register();
        mapperFactory.classMap(Prenda.class, PrendaResponse.class)
        .customize(new CustomMapper<Prenda, PrendaResponse>() {
            public void mapAtoB(final Prenda prenda, final PrendaResponse prendaResponse, final MappingContext context) {
                LOGGER.info(" #### Custom mapping to Prenda and PrendaResponse #### ");
                prendaResponse.setId(prenda.getId());
                prendaResponse.setDescripcion(prenda.getDescripcion());
                prendaResponse.setTipo(prenda.getTipo().getDescripcion());
                prendaResponse.setPrecioBase(prenda.getPrecioBase());
            }
        }).register();


        return mapperFactory.getMapperFacade();
    }
}
