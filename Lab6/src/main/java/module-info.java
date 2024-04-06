module org.example.lab {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    //requires javafaker;

   opens org.example.lab6JavaFX to javafx.fxml;
   exports org.example.lab6JavaFX;
}