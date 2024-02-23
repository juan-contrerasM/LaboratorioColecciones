package co.edu.uniquindio.laboratoriocolecciones.model;


import lombok.*;

//etiquetas que nos ayudan a generar ocultamente

@Data// get y setter
@AllArgsConstructor //contructor con todos lo atributos
@NoArgsConstructor//constructor vacio
@ToString// el to string
@Builder // es aquel que nos permite acceder a estos metodos lombok

public class CarritoCompras {

    private String codigo;

}
