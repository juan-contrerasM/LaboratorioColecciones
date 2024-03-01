module co.edu.uniquindio.laboratorioColecciones
{
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires lombok;
    requires java.logging;
    requires java.desktop;

    opens co.edu.uniquindio.laboratoriocolecciones.controllersView to javafx.fxml;
    exports co.edu.uniquindio.laboratoriocolecciones.controllersView;
    opens co.edu.uniquindio.laboratoriocolecciones.model;
    exports co.edu.uniquindio.laboratoriocolecciones.model;

}