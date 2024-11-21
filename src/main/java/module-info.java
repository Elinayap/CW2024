module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    // Open specific subpackages for reflection by JavaFX
    opens com.example.demo.controller to javafx.fxml;
    opens com.example.demo.actors to javafx.fxml;
    opens com.example.demo.projectiles to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.assets to javafx.fxml;
    opens com.example.demo.destructible to javafx.fxml;

    // Export all relevant packages for use across the application
    exports com.example.demo.controller;
    exports com.example.demo.actors;
    exports com.example.demo.projectiles;
    exports com.example.demo.levels;
    exports com.example.demo.assets;
    exports com.example.demo.destructible;
}
