package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import co.edu.uniquindio.laboratoriocolecciones.model.CarritoCompras;
import co.edu.uniquindio.laboratoriocolecciones.model.Producto;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.GestorProductos;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;

public class Crud3CarritodeCompras {

    @FXML
    private TextField codigoProducto;
    @FXML
    private ListView<String> listaProductos;

    private CarritoCompras carritoDeCompras = new CarritoCompras();
    private HashMap<String, Producto> productosDisponibles = GestorProductos.getProductos();

    @FXML
    protected void agregarProductoPorCodigo() {
        String codigo = codigoProducto.getText();
        productosDisponibles=GestorProductos.getProductos();

        Producto producto = productosDisponibles.get(codigo);

        if (producto != null) {
            carritoDeCompras.agregarProducto(producto, 1);
            listaProductos.getItems().add(producto.getNombreProducto() + " ProductoEjemplo");
            codigoProducto.clear();

            // Intenta guardar el carrito actualizado en el archivo.
            try {
                Persistencia.guardarCarritoCompras(carritoDeCompras);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
    @FXML
    protected void eliminarProductoSeleccionado() {
        String codigo = listaProductos.getSelectionModel().getSelectedItem();
        carritoDeCompras.eliminarProductoDelCarrito(codigo);
        actualizarListaProductos();
    }
    @FXML
    private void actualizarListaProductos() {
        listaProductos.getItems().clear();
        for (Producto producto : carritoDeCompras.getProductos().values()) {
            listaProductos.getItems().add(producto.getCodigo() + " - " + producto.getNombreProducto());
        }
    }
}

