package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import co.edu.uniquindio.laboratoriocolecciones.model.Cliente;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.Persistencia;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Crud2ClientesController  implements Initializable {

    private int seleccion;

    ObservableList<Cliente> obsClientes;
    private ArrayList<Cliente> listaArrayCliente = new ArrayList<>();

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
    private TableColumn<Cliente, String> columnaDireccion;

    @FXML
    private TableColumn<Cliente, String> columnaNIdentificacion;

    @FXML
    private TableColumn<Cliente, String> columnaNombre;

    @FXML
    private TableView<Cliente> tabla_detalle_cliente;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txt_direccion;

    @FXML
    private TextField txt_numero_identificacion;
    HashMap<String, Cliente> clientes = new HashMap<>();
    HashMap<String, Cliente> listaClientes=new HashMap<>();


    @FXML
    void agregar_cliente(ActionEvent event) throws IOException {
        String nombre=txtNombre.getText();
        String id=txt_numero_identificacion.getText();
        String direccion=txt_direccion.getText();
        if(verificarTxtLlenosCliente()) {

            Cliente cliente = new Cliente(id, nombre, direccion);
            listaClientes.put(cliente.getNumeroId(), cliente);
            obsClientes = tabla_detalle_cliente.getItems();
            obsClientes.add(cliente);

            Persistencia.guardarClientes(listaClientes);
            listaArrayCliente.addAll(Persistencia.cargarClientes());
            String identificacion= listaArrayCliente.get(0).getNumeroId();
            clientes.put(id ,clientes.get(0));
            mostrarEnTabla(listaArrayCliente);



            JOptionPane.showMessageDialog(null, "Ingresado con exito");

        }else {
            JOptionPane.showMessageDialog(null, "Por favor verifique que los campos contengan todos los datos");
        }
        limpiarEspacios();

    }
    public void mostrarEnTabla(ArrayList<Cliente> miLista){
//
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columnaNIdentificacion.setCellValueFactory(new PropertyValueFactory<>("numeroId"));
        tabla_detalle_cliente.setItems(FXCollections.observableArrayList(miLista));

    }


    @FXML
    void editar_cliente(ActionEvent event) {


try {
    listaArrayCliente.get(seleccion).setNombre(txtNombre.getText());
    listaArrayCliente.get(seleccion).setNumeroId(txt_numero_identificacion.getText());
    listaArrayCliente.get(seleccion).setDireccion(txt_direccion.getText());
    tabla_detalle_cliente.refresh();
}catch (RuntimeException e) {
    JOptionPane.showMessageDialog(null, "Error, debe selecionar un elemento");

}




    }


    @FXML
    void eliminar_cliente(ActionEvent event) {
        try {listaArrayCliente.remove(seleccion);
            tabla_detalle_cliente.getItems().remove(seleccion);


        }catch (RuntimeException e){
            JOptionPane.showMessageDialog(null,"Error, debe selecionar un elemento");
        }

    }

    public boolean verificarTxtLlenosCliente(){

        String nombre = txtNombre.getText();
        String id = txt_numero_identificacion.getText();
        String direccion =txt_direccion.getText();

        if(nombre== null || nombre.equals("")|| id == null || id.equals("")||
        direccion == null || direccion.equals("")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listaArrayCliente.addAll(Persistencia.cargarClientes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mostrarEnTabla(listaArrayCliente);
    }
    public void limpiarEspacios(){
        txtNombre.setText("");
        txt_direccion.setText("");
        txt_numero_identificacion.setText("");
    }

    public void seleccionarCliente(javafx.scene.input.MouseEvent mouseEvent) {
       try {
           seleccion = tabla_detalle_cliente.getSelectionModel().getSelectedIndex();
           txtNombre.setText(listaArrayCliente.get(seleccion).getNombre());
           txt_direccion.setText(listaArrayCliente.get(seleccion).getDireccion());
           txt_numero_identificacion.setText(listaArrayCliente.get(seleccion).getNumeroId());
       }catch(RuntimeException e){
           JOptionPane.showMessageDialog(null, "Error, selecciono un elemento en blanco");

       }
    }
}

