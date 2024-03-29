package co.edu.uniquindio.laboratoriocolecciones.model;


import lombok.*;

@Data// get y setter
@AllArgsConstructor //contructor con todos lo atributos
@NoArgsConstructor//constructor vacio
@ToString// el to string
@Builder // es aquel que nos permite acceder a estos metodos lombok

public class Producto {
    private String codigo;
    private String nombreProducto;
    private double precio;
    private int cantidad;

}

