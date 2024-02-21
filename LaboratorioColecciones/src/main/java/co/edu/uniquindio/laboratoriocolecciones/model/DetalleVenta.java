package co.edu.uniquindio.laboratoriocolecciones.model;

public class DetalleVenta {

    private Integer cantidad;
    private String subtotal;

    public DetalleVenta(Integer cantidad, String subtotal) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
