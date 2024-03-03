package co.edu.uniquindio.laboratoriocolecciones.persistencia;

import co.edu.uniquindio.laboratoriocolecciones.model.Producto;

import java.util.HashMap;

public class GestorProductos {
    private static HashMap<String, Producto> productos = new HashMap<>();

    public static void inicializarProductos() {
        try {
            productos = Persistencia.cargarProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static HashMap<String, Producto> getProductos() {
        return productos;
    }
    public static Producto obtenerProductoPorCodigo(String codigo) {
        return productos.get(codigo);
    }
}


