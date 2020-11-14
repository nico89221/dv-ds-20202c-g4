package ar.edu.davinci.dvds20202cg4.model;

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
@PrimaryKeyJoinColumn(name = "vnt_id")
@DiscriminatorValue("EFECTIVO")
@Table(name="ventas_efectivo")


@Data
@SuperBuilder
public class VentaEfectivo extends Venta {

    @Override
    public Double conRecargo(Double importeBase) {
        return importeBase;
    }

}
