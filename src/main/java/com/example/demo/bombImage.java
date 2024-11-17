package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bombImage extends ImageView {
    
    private static final String IMAGE_NAME = "/images/bomb.png";
    public static final int BOMB_SIZE = 150;
    
    public bombImage(double xPosition, double yPosition) {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setImage(new Image(getClass().getResource("/com/example/demo/images/bomb.png").toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(BOMB_SIZE);
        this.setFitWidth(BOMB_SIZE);
    }
}