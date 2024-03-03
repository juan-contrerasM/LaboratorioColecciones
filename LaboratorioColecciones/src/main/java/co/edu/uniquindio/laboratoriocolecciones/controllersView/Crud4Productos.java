package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import co.edu.uniquindio.laboratoriocolecciones.model.Producto;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.HashMap;


public class Crud4Productos {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;

    @FXML
    void guardarProducto(javafx.event.ActionEvent actionEvent) throws IOException {
        // Crea un nuevo HashMap para almacenar el producto que se va a agregar
        HashMap<String, Producto> listaProductos = new HashMap<>();

        // Obtiene los datos del producto desde los campos de texto
        String codigo = txtCodigo.getText();
        String nombreProducto = txtNombreProducto.getText();
        double precio = Double.parseDouble(txtPrecio.getText()); // Asegúrate de manejar NumberFormatException
        int cantidad = Integer.parseInt(txtCantidad.getText()); // Asegúrate de manejar NumberFormatException

        // Crea un nuevo objeto Producto
        Producto producto = new Producto(codigo, nombreProducto, precio, cantidad);

        // Agrega el producto al HashMap
        listaProductos.put(producto.getCodigo(), producto);

        // Guarda el HashMap de productos utilizando el método en Persistencia
        Persistencia.guardarProductos(listaProductos);

        // Opcional: Cargar y mostrar todos los productos para confirmación
        HashMap<String, Producto> productosCargados = Persistencia.cargarProductos();
        for (Producto productoCargado : productosCargados.values()) {
            System.out.println(productoCargado.toString()); // Asegúrate de tener implementado el método toString en la clase Producto
        }

        // Limpia los campos después de guardar el producto
        limpiarCampos();
    }
    private void limpiarCampos() {
        // Limpia los campos después de guardar un producto
        txtCodigo.clear();
        txtNombreProducto.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }
}

