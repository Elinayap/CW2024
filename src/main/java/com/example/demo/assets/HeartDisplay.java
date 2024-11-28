package com.example.demo.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HeartDisplay {

    private static final String HEART_IMAGE_NAME = "/com/example/demo/images/hearts.png";
    private static final int HEART_HEIGHT = 50;

    private final HBox heartContainer; 
    private final double containerXPosition;
    private final double containerYPosition;
    

    public HeartDisplay(double xPosition, double yPosition, int initialHeartCount) {
        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        this.heartContainer = new HBox(); 
        this.heartContainer.setLayoutX(containerXPosition);
        this.heartContainer.setLayoutY(containerYPosition);
        updateHeartCount(initialHeartCount); 
    }

    public HBox getContainer() {
        return heartContainer;
    }

    public void updateHeartCount(int hearts) {
		if (hearts < 0) {
			throw new IllegalArgumentException("Heart count cannot be negative: " + hearts);
		}
	
		// Clear current hearts
		heartContainer.getChildren().clear();
	
		// Add hearts based on the count
		for (int i = 0; i < hearts; i++) {
			try {
				ImageView heartImage = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
				heartImage.setFitHeight(HEART_HEIGHT);
				heartImage.setPreserveRatio(true);
				heartContainer.getChildren().add(heartImage);
			} catch (NullPointerException e) {
				throw new IllegalArgumentException("Heart image not found: " + HEART_IMAGE_NAME, e);
			}
		}
	}
	

    public void removeHeart() {
        if (!heartContainer.getChildren().isEmpty()) {
            heartContainer.getChildren().remove(heartContainer.getChildren().size() - 1); // Remove the last heart
        }
    }

    public void addHeart() {
        ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
        heart.setFitHeight(HEART_HEIGHT);
        heart.setPreserveRatio(true);
        heartContainer.getChildren().add(heart);
    }

     // Reset the number of hearts displayed
     public void resetHearts(int heartsToDisplay) {
        updateHeartCount(heartsToDisplay);
    }
}
