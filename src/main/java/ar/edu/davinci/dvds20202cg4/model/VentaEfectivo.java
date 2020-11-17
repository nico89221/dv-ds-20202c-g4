package ar.edu.davinci.dvds20202cg4.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Calcular Venta en Efectivo
 * 
 * @author Grupo 4
 *
 */

@Entity
@PrimaryKeyJoinColumn(name = "vta_id")
@DiscriminatorValue("EFECTIVO")
@Table(name="ventas_efectivo")


@Data
@SuperBuilder
public class VentaEfectivo implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = -8393218825317899807L;


    
    public Double conRecargo(Double importeBase) {
        return importeBase;
    }

}
