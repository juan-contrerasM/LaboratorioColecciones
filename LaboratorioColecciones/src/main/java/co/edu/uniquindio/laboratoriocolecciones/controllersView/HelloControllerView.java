package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import co.edu.uniquindio.laboratoriocolecciones.model.Cliente;
import co.edu.uniquindio.laboratoriocolecciones.model.Producto;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.Persistencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloControllerView implements Initializable {



    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnAgregar1;

    @FXML
    private DatePicker dateVenta;

    @FXML
    private ImageView imgBuscador;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private Pane paneBotonesInfoCliente;

    @FXML
    private Pane paneBotonesVenta;

    @FXML
    private Pane paneDetalleVenta;

    @FXML
    private Pane paneInfoCliente;

    @FXML
    private Pane paneInfoVenta;

    @FXML
    private Pane paneMensajeBienvenida;

    @FXML
    private Pane paneOpciones;

    @FXML
    private AnchorPane panePrincipal;

    @FXML
    private Pane paneTable;

    @FXML
    private TableView<?> tableListado;

    @FXML
    private TextField txtBuscador;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private Text txtInformativo;

    @FXML
    private Text txtInformativo1;

    @FXML
    private Text txtInformativo11;

    @FXML
    private Text txtInformativo2;

    @FXML
    private Text txtInformativo3;




    public void  guardarCliente() throws IOException {
        HashMap<String, Cliente>listaClientes=new HashMap<>();
        Cliente cliente= new Cliente("1","juan","123456789");
        listaClientes.put(cliente.getNumeroId(), cliente);

        Persistencia.guardarClientes(listaClientes);

        HashMap<String,Cliente> clientes= new HashMap<>();
        clientes=Persistencia.cargarClientes();
        for (Cliente cliente1: clientes.values()){
            System.out.println(cliente1.toString());
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            guardarCliente();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


