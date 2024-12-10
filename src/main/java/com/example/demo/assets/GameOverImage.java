package com.example.demo.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the Game Over image.
 * Extends {@link ImageView} to allow adjust position and properties of image.
 */
public class GameOverImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
     * Constructs a GameOverImage with the initial position.
     *
     * @param xPosition The initial x-coordinate of the image.
     * @param yPosition The initial y-coordinate of the image.
     */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()) );
//		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

}

