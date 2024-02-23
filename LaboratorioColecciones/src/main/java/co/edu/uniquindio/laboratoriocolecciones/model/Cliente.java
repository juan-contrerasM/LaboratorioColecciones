package co.edu.uniquindio.laboratoriocolecciones.model;

import lombok.*;

@Data// get y setter
@AllArgsConstructor //contructor con todos lo atributos
@NoArgsConstructor//constructor vacio
@ToString// el to string
@Builder // es aquel que nos permite acceder a estos metodos lombok
public class Cliente {


    private String numeroId;
    private String nombre;
    private String direccion;


}
