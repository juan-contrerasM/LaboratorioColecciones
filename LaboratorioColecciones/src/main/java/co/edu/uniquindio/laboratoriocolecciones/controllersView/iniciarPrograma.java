package co.edu.uniquindio.laboratoriocolecciones.controllersView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class iniciarPrograma extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(iniciarPrograma.class.getResource("crud.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1110, 550);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}