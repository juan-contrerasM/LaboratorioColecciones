package co.edu.uniquindio.laboratoriocolecciones.model;

import java.util.HashMap;
import java.util.Map;
import lombok.*;

//etiquetas que nos ayudan a generar ocultamente

@Data// get y setter
@AllArgsConstructor // contructor con todos lo atributos
@ToString// el to string
@Builder // es aquel que nos permite acceder a estos metodos lombok

public class CarritoCompras {
    private Map<String, Producto> productos;
    private Map<String, Integer> cantidades;


    public CarritoCompras() {
        this.productos = new HashMap<>();
        this.cantidades = new HashMap<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (productos.containsKey(producto.getCodigo())) {
            // Si el producto ya existe en el carrito, solo actualizamos la cantidad
            cantidades.put(producto.getCodigo(), cantidades.get(producto.getCodigo()) + cantidad);
        } else {
            // Si es un nuevo producto, lo agregamos al carrito y establecemos su cantidad
            productos.put(producto.getCodigo(), producto);
            cantidades.put(producto.getCodigo(), cantidad);
        }
    }
    public void eliminarProductoDelCarrito(String codigoProducto) {
        if (this.containsKey(codigoProducto)) {
            this.remove(codigoProducto);
            System.out.println("Producto eliminado del carrito.");
        } else {
            System.out.println("El producto no existe en el carrito.");
        }
    }
    public boolean containsKey(String codigoProducto) {
        return productos.containsKey(codigoProducto);
    }
    public void remove(String codigoProducto) {
        productos.remove(codigoProducto);
        cantidades.remove(codigoProducto);
    }
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Productos en el carrito:");
            for (Map.Entry<String, Integer> entrada : cantidades.entrySet()) {
                Producto producto = productos.get(entrada.getKey());
                System.out.println("Código: " + producto.getCodigo() + ", Nombre: " + producto.getNombreProducto() + ", Precio: " + producto.getPrecio() + ", Cantidad: " + entrada.getValue());
            }
        }
    }
}

