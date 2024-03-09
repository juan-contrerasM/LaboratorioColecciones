package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import co.edu.uniquindio.laboratoriocolecciones.model.Cliente;
import co.edu.uniquindio.laboratoriocolecciones.model.DetalleVenta;
import co.edu.uniquindio.laboratoriocolecciones.model.Empresa;
import co.edu.uniquindio.laboratoriocolecciones.model.Venta;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.ToString;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class crud1VentaController implements Initializable {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnGuardarDetalleVenta;
    @FXML
    private DatePicker dateVenta;

    @FXML
    private ImageView imgBuscador;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private Pane paneBotonesVenta;

    @FXML
    private Pane paneDetalleVenta;

    @FXML
    private Pane paneInfoCliente;

    @FXML
    private Pane paneInfoVenta;

    @FXML
    private Button btnGuardarDetalleVenta1;
    @FXML
    private Pane paneLabel;

    @FXML
    private Pane paneLabel3;

    @FXML
    private Pane paneMensajeBienvenida;

    @FXML
    private Pane paneOpciones;

    @FXML
    private AnchorPane panePrincipal;

    @FXML
    private Pane paneTable;

    @FXML
    private Pane panelabel2;

    @FXML
    private Tab tabAñadirProducto;

    @FXML
    private Tab tabCarritoCompras;


    @FXML
    private TextField txtBuscador;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCodigoDetalleVenta;

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

    @FXML
    private TextField txtSubTotal;

    @FXML
    private TableColumn<DetalleVenta, String> columnCantidad;

    @FXML
    private TableColumn<DetalleVenta, Integer> columnCodigoVenta;
    @FXML
    private TableColumn<DetalleVenta, String> columSubTotal;

    @FXML
    private TableView<DetalleVenta> tableListado;


    //----------------------------------------------------------


    private ArrayList<DetalleVenta> detalleVentaList = new ArrayList<>();

    private ArrayList<Venta> ventaList = new ArrayList<>();

    private HashMap<String, Cliente> listaClienbtes=new HashMap<>();

    @FXML
    void GuardarDetalleVenta(ActionEvent event) throws IOException {
        //mostrar en tabla y guardar en archivos txt
        if (validarCamposGudarDetalleVenta()) {

            DetalleVenta detalleVenta = new DetalleVenta();
            // se setean la info de la interfaz en el objeto
            detalleVenta.setSubtotal(txtSubTotal.getText());
            detalleVenta.setCantidad(Integer.valueOf(txtCantidad.getText()));
            detalleVenta.setCodigoDetaleVenta(txtCodigoDetalleVenta.getText());

            //se obtiene la lista del txt
            detalleVentaList = Persistencia.cargarDetalleVenta();

            //se verifica que el detalle venta no este contenido en la lista
            if (existeDetalleVenta()) {
                mostrarMensaje("Registro Detalle", "Registro Detalle Venta erroneo", "El codigo de Detalle Venta ya fue registrado, cambie el codigo", Alert.AlertType.ERROR);

            } else {
                detalleVentaList.add(detalleVenta);
                mostrarMensaje("Se guardo Detalle", "Se ha guardado correctamente ", "S eha gusrado correctamente el detalle de la venta", Alert.AlertType.INFORMATION);
                tableListado.setItems(FXCollections.observableArrayList(detalleVentaList));
                limpiarCamposDetalle();
            }
            Persistencia.guardarDetalleVenta(detalleVentaList);

        }

    }

    @FXML
    void agregarVenta(ActionEvent event) throws IOException {
        //guardar los datos en su totalidad
        if (validarCamposGuardarVenta()) {
            Venta venta = new Venta();
            ventaList = Persistencia.cargarVenta();

            venta.setCodigoVenta(txtCodigo.getText());
            venta.setFechaVenta(dateVenta.getValue());
            if (buscarCliente() == null) {
                mostrarMensaje("Error busqueda", " Error al registrar una venta", "error de busqueda de la informacion del cliente con esa identificaicon, no existe", Alert.AlertType.INFORMATION);
            } else {
                venta.setCliente(buscarCliente());
                if (buscarDetalleVenta() == null) {
                    mostrarMensaje("Error busqueda", "registrar un detalle venta", "debe registrar un detalle venta con este codigo", Alert.AlertType.INFORMATION);
                } else {
                    venta.setDetalleVenta(buscarDetalleVenta());
                    if (buscarVenta()) {
                        mostrarMensaje("Error al guardar venta", "Codigo de venta ya utilizaedo", "Ya ha sido  registrada una venta con este codigo", Alert.AlertType.ERROR);
                    } else {
                        ventaList.add(venta);
                        mostrarMensaje("Se guardo la venta", "Venta guardada con exito", "La venta ha sido guarda", Alert.AlertType.INFORMATION);
                        limpiarCamposVenta();
                        Persistencia.guardarVenta(ventaList);
                        Persistencia.guardarHistoricoVenta(ventaList);
                    }
                }
            }
        }
        for (int i = 0; i < ventaList.size(); i++) {
            System.out.println(ventaList.get(i).toString());
        }
        System.out.println("--------------------------------");


    }

    @FXML
    void buscar(MouseEvent event) {
        List<DetalleVenta> AUXILIAR = new ArrayList<>();
        for (DetalleVenta detalleVenta : detalleVentaList) {
            if (detalleVenta.getCodigoDetaleVenta().equals(txtBuscador.getText())) {
                AUXILIAR.add(detalleVenta);
                break;
            }
        }
        tableListado.setItems(FXCollections.observableArrayList(AUXILIAR));

    }
    private ArrayList<Cliente> lista= new ArrayList<>();
    public Cliente buscarCliente() throws IOException {
        lista = Persistencia.cargarClientes();
        for(int i=0; i<lista.size();i++){
            listaClienbtes.put(lista.get(i).getNumeroId(),lista.get(i));
        }
        Persistencia.guardarClientes(listaClienbtes);
        return listaClienbtes.get(txtIdentificacion.getText());
    }
    public boolean existeDetalleVenta(){
        boolean existeDetalle = detalleVentaList.stream()
                .anyMatch(detalleVenta -> detalleVenta.getCodigoDetaleVenta().equals(txtCodigoDetalleVenta.getText()));
        return existeDetalle;// Ahora 'existeVenta' contiene true si hay una venta con el código especificado, de lo contrario, contiene false


    }
    public DetalleVenta buscarDetalleVenta() throws IOException {
        detalleVentaList = Persistencia.cargarDetalleVenta();
        Persistencia.guardarDetalleVenta(detalleVentaList);
        for (DetalleVenta detalleVenta1 : detalleVentaList) {
            if (detalleVenta1.getCodigoDetaleVenta().equals(txtBuscador.getText())) {
                return detalleVenta1;
            }
        }
        return null;
    }
    public boolean buscarVenta() {
        boolean existeVenta = ventaList.stream()
                .anyMatch(venta1 -> venta1.getCodigoVenta().equals(txtCodigo.getText()));
        return existeVenta;// Ahora 'existeVenta' contiene true si hay una venta con el código especificado, de lo contrario, contiene false
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            detalleVentaList = Persistencia.cargarDetalleVenta();
            columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            columnCodigoVenta.setCellValueFactory(new PropertyValueFactory<>("codigoDetaleVenta"));
            columSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

            // Enlazar la tabla a la lista observable
            tableListado.setItems(FXCollections.observableArrayList(detalleVentaList));
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

    private boolean validarCamposGudarDetalleVenta() {
        String mensaje = "";
        if (txtSubTotal.getText() == null || txtSubTotal.getText().equals("")) {
            mensaje += "El campo Codigo Seccion detalle venta no ha sido llenado \n";
        } else {
            try {
                Integer.parseInt(txtCantidad.getText());
            } catch (NumberFormatException e) {
                mensaje += "El campo Cantidad Seccion detalle venta  debe ser un número entero \n";
            }
        }
        if (txtCantidad.getText() == null || txtCantidad.getText().equals("")) {
            mensaje += "El cambo Cantidad no ha sido llenado \n";
        }
        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Campos sin llenar", "Campos vacios", mensaje, Alert.AlertType.WARNING);
            return false;
        }

    }

    private boolean validarCamposGuardarVenta() {
        String mensaje = "";
        if (txtIdentificacion.getText() == null || txtIdentificacion.getText().equals("")) {
            mensaje += "El cambo Identificacion no ha sido llenado \n";
        }

        if (txtCodigo.getText() == null || txtCodigo.getText().equals("")) {
            mensaje += "El campo Codigo seccion informacion venta no ha sido llenado \n";
        }

        if (dateVenta.getValue() == null) {
            mensaje += "Selecionar una fecha de venta";
        }
        if (mensaje == "") {
            return true;
        } else {
            mostrarMensaje("Campos sin llenar", "Campos vacios", mensaje, Alert.AlertType.WARNING);
            return false;
        }

    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private void limpiarCamposVenta() {
        txtBuscador.setText("");
        txtCantidad.setText("");
        txtCodigo.setText("");
        txtSubTotal.setText("");
        txtCodigoDetalleVenta.setText("");
        txtIdentificacion.setText("");
        dateVenta.setValue(null);
    }

    private void limpiarCamposDetalle() {
        txtCantidad.setText("");
        txtCodigoDetalleVenta.setText("");
        txtSubTotal.setText("");

    }

    @FXML
    void refrescarTabla(ActionEvent event) throws IOException {
        detalleVentaList = Persistencia.cargarDetalleVenta();
        tableListado.setItems(FXCollections.observableArrayList(detalleVentaList));

    }

}


