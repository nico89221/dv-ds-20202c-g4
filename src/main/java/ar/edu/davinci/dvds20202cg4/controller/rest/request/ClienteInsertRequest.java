package ar.edu.davinci.dvds20202cg4.controller.rest.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteInsertRequest {
    
    private String nombre;

    private String apellido;

}
