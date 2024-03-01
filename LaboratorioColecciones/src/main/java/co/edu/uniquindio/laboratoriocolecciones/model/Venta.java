package co.edu.uniquindio.laboratoriocolecciones.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data// get y setter
@AllArgsConstructor //contructor con todos lo atributos
@NoArgsConstructor//constructor vacio
@ToString// el to string
@Builder
public class Venta {
    private String codigoVenta;
    private Cliente cliente;
    private LocalDate fechaVenta;
    private DetalleVenta detalleVenta;
}
