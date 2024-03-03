package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import co.edu.uniquindio.laboratoriocolecciones.model.CarritoCompras;
import co.edu.uniquindio.laboratoriocolecciones.model.Producto;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.GestorProductos;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
        Producto producto = productosDisponibles.get(codigo);

        if (producto != null) {
            carritoDeCompras.agregarProducto(producto, 1); // Asume que este método existe y agrega el producto al carrito.
            listaProductos.getItems().add(producto.getNombreProducto() + " ProductoEjemplo");
            // Actualiza la UI como sea necesario, por ejemplo, limpiando el campo de texto.
            codigoProducto.clear();
        } else {
            // Mostrar mensaje al usuario de que el producto no se encontró.
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

