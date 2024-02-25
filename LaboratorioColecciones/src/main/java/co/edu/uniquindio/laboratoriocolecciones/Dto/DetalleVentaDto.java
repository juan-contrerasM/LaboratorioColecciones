package co.edu.uniquindio.laboratoriocolecciones.Dto;

import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class DetalleVentaDto implements Serializable {

    private String numeroId;
    private String nombre;
    private String subtotal;
    private String codigo;
    private String nombreProducto;
    private Integer precio;
    private Integer cantidad;
}
