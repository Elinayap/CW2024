package com.example.demo.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a win image after user win the game.
 * Image is only visible if user won the game.
 */
public class WinImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;
	
	/**
     * Constructs a WinImage object at a specified position.
     * 
     * @param xPosition The X-coordinate for the image position.
     * @param yPosition The Y-coordinate for the image position.
     */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}
	
	/**
     * WinImage visible on screen.
     */
	public void showWinImage() {
		this.setVisible(true);
	}

}
