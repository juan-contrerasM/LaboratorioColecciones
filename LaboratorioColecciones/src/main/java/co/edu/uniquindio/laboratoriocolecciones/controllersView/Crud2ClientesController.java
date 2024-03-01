package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import co.edu.uniquindio.laboratoriocolecciones.model.Cliente;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public class Crud2ClientesController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ImageView img_detalle_venta;

    @FXML
    private Pane pane_btn_lista_clientes;

    @FXML
    private Pane pane_detalleCliente;

    @FXML
    private Pane pane_lista_cliente;

    @FXML
    private Pane pane_titulo_detalle_cliente;

    @FXML
    private Pane pane_titulo_list_clientes;

    @FXML
    private TableColumn<?, ?> tabla_detalle_cliente;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_numero_identificacion;

    @FXML
    void agregar_cliente(ActionEvent event) throws IOException {
        HashMap<String, Cliente> listaClientes=new HashMap<>();
        String nombre=txtNombre.getText();
        String id=txt_numero_identificacion.getText();
        String direccion=txt_direccion.getText();

        Cliente cliente= new Cliente(id,nombre,direccion);
        listaClientes.put(cliente.getNumeroId(), cliente);

        Persistencia.guardarClientes(listaClientes);

        HashMap<String,Cliente> clientes= new HashMap<>();
        clientes=Persistencia.cargarClientes();
        for (Cliente cliente1: clientes.values()){
            System.out.println(cliente1.toString());
        }

    }

    @FXML
    void editar_cliente(ActionEvent event) {

    }

    @FXML
    void eliminar_cliente(ActionEvent event) {

    }

}

