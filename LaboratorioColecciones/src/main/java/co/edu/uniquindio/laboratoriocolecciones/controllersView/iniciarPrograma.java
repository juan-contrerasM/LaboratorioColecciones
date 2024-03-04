package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import co.edu.uniquindio.laboratoriocolecciones.model.Venta;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.GestorProductos;
import co.edu.uniquindio.laboratoriocolecciones.persistencia.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;

public class iniciarPrograma extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(iniciarPrograma.class.getResource("crud.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1110, 555);
        stage.setTitle("Ventas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        GestorProductos.inicializarProductos(); // Añade esta línea para inicializar los productos
        LinkedList<Venta> historicoVentas = Persistencia.cargarHistoricoVentas();
        launch(args);
    }
}