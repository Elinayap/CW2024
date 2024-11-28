package com.example.demo.levels;

import com.example.demo.assets.GameOverImage;
import com.example.demo.assets.HeartDisplay;
import com.example.demo.assets.WinImage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelView {

    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private static final int WIN_IMAGE_X_POSITION = 355;
    private static final int WIN_IMAGE_Y_POSITION = 175;
    private static final int LOSS_SCREEN_X_POSITION = -160;
    private static final int LOSS_SCREEN_Y_POSITION = -375;
    private static final double SCORE_LABEL_X_POSITION = 700; // Position the score label
    private static final double SCORE_LABEL_Y_POSITION = 25;

    private final Group root;
    private final WinImage winImage;
    private final GameOverImage gameOverImage;
    private final HeartDisplay heartDisplay;
    private final Label scoreLabel; // Add a score label

    public LevelView(Group root, int heartsToDisplay) {
        this.root = root;
        this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
        this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);

        this.scoreLabel = new Label("Score: 0");
        this.scoreLabel.setFont(new Font("Arial", 20));
        this.scoreLabel.setTextFill(Color.WHITE);
        this.scoreLabel.setLayoutX(SCORE_LABEL_X_POSITION);
        this.scoreLabel.setLayoutY(SCORE_LABEL_Y_POSITION);
    }

    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    public void showWinImage() {
        root.getChildren().add(winImage);
        winImage.showWinImage();
    }

    public void showGameOverImage() {
        if (!root.getChildren().contains(gameOverImage)) {
            root.getChildren().add(gameOverImage);
        }
    }

    public void clearGameView() {
        root.getChildren().removeIf(node -> node != gameOverImage);
    }

    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }

    // Update the score
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score); 
    }

    // Add score label to root
    public void showScoreDisplay() {
        if (!root.getChildren().contains(scoreLabel)) {
            root.getChildren().add(scoreLabel);
        }
    }

    // Method to update the heart display
    public void updateHeartDisplay(int hearts) {
        heartDisplay.updateHeartCount(hearts);
        System.out.println("Updated hearts to: " + hearts);
    }

     // Method to reset hearts
     public void resetHearts(int heartsToDisplay) {
        heartDisplay.resetHearts(heartsToDisplay);
        System.out.println("Hearts reset to: " + heartsToDisplay);
    }
}

