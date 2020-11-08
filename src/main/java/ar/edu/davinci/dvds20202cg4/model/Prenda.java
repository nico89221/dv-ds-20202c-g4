package ar.edu.davinci.dvds20202cg4.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Prenda
 * 
 * @author grupo 4
 *
 */

@Entity
@Table(name="prendas")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "prd_id")
    private Long id;
    //private Estado estado;

    @Column(name = "prd_precio_base")
    private BigDecimal precioBase;
    
    @Column(name = "prd_tipo_prenda")
    @Enumerated(EnumType.STRING)
    private TipoPrenda tipo;
    
    @Column(name = "prd_description")
    private String descripcion;
}
  