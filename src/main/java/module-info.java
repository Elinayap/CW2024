module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    // Export and open the main package that contains the application entry point
    opens com.example.demo.controller to javafx.fxml;
    exports com.example.demo.controller;
}
