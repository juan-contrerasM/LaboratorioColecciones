module co.edu.uniquindio.laboratoriocolecciones {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens co.edu.uniquindio.laboratoriocolecciones to javafx.fxml;
    exports co.edu.uniquindio.laboratoriocolecciones;
    exports co.edu.uniquindio.laboratoriocolecciones.controllersView;
    opens co.edu.uniquindio.laboratoriocolecciones.controllersView to javafx.fxml;
}