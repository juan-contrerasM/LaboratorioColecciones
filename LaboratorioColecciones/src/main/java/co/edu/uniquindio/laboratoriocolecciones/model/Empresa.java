package co.edu.uniquindio.laboratoriocolecciones.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data// get y setter
@AllArgsConstructor //contructor con todos lo atributos
@NoArgsConstructor//constructor vacio
@ToString// el to string
@Builder // es aquel que nos permite acceder a estos metodos lombok
public class Empresa  {
   private List<Venta>listaVentas=new ArrayList<>();
   private List<DetalleVenta>listasDetalleVentas= new ArrayList<>();
}
