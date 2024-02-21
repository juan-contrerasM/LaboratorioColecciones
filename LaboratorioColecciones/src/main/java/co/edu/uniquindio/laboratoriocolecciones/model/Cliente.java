package co.edu.uniquindio.laboratoriocolecciones.model;

public class Cliente {


    private String numeroId;
    private String nombre;
    private String direccion;

    public Cliente(String numeroId, String nombre, String direccion) {
        this.numeroId = numeroId;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
