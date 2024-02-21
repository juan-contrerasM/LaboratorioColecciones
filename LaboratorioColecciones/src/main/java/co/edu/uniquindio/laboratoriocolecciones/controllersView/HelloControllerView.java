package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloControllerView  implements Initializable {

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    void Registrar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
