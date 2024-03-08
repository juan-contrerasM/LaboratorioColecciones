package co.edu.uniquindio.laboratoriocolecciones.persistencia;

import co.edu.uniquindio.laboratoriocolecciones.model.Producto;

import java.util.HashMap;

public class GestorProductos {
    private static HashMap<String, Producto> productos = new HashMap<>();
    public static void inicializarProductos() {

    }
    public static HashMap<String, Producto> getProductos() {
        productos=Persistencia.cargarProductos();
        return productos;
    }
    public static Producto obtenerProductoPorCodigo(String codigo) {
        return productos.get(codigo);
    }
}


