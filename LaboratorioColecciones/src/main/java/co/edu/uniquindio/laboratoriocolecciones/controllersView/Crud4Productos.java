package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static co.edu.uniquindio.laboratoriocolecciones.persistencia.Persistencia.RUTA_ARCHIVO_PRODUCTOS;

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
    protected void guardarProducto() {
        if (validarDatos()) {
            String codigo = txtCodigo.getText();
            String nombreProducto = txtNombreProducto.getText();
            String precio = txtPrecio.getText();
            String cantidad = txtCantidad.getText();

            String lineaProducto = String.format("%s,%s,%s,%s%n", codigo, nombreProducto, precio, cantidad);

            try {
                Path path = Paths.get(RUTA_ARCHIVO_PRODUCTOS);
                Files.write(path, lineaProducto.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                limpiarCampos();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText(null);
                alert.setContentText("El producto ha sido guardado correctamente.");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al guardar el producto");
                alert.setContentText("Ocurrió un error al intentar guardar el producto. Por favor, inténtelo de nuevo.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos inválidos");
            alert.setHeaderText(null);
            alert.setContentText("Los datos ingresados no son válidos. Por favor, verifique la información proporcionada.");
            alert.showAndWait();
        }
    }
    private boolean validarDatos() {
        return !txtCodigo.getText().isEmpty() &&
                !txtNombreProducto.getText().isEmpty() &&
                !txtPrecio.getText().isEmpty() &&
                !txtCantidad.getText().isEmpty();
    }
    private void limpiarCampos() {
        // Limpia los campos después de guardar un producto
        txtCodigo.clear();
        txtNombreProducto.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }
}

