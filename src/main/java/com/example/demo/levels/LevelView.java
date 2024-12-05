package com.example.demo.levels;

import com.example.demo.assets.GameOverImage;
import com.example.demo.assets.HeartDisplay;
import com.example.demo.assets.WinImage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The LevelView class represent the appearance of the level.
 * About the heart displays, win or lose images, and the score label.
 */
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

    /**
     * Constructs a LevelView instance.
     *
     * @param root The root where all level components are displayed.
     * @param heartsToDisplay The number of hearts to display.
     */
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

    /**
     * Displays the heart container.
     */
    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    /**
     * Displays the win image on the screen.
     */
    public void showWinImage() {
        root.getChildren().add(winImage);
        winImage.showWinImage();
    }

    /**
     * Displays the game over image on the screen.
     */
    public void showGameOverImage() {
        if (!root.getChildren().contains(gameOverImage)) {
            root.getChildren().add(gameOverImage);
        }
    }

    /**
     * Clears all game view except the game over image.
     */
    public void clearGameView() {
        root.getChildren().removeIf(node -> node != gameOverImage);
    }

    /**
     * Updates the heart display by removing hearts from the container.
     *
     * @param heartsRemaining The remaining of hearts that should displayed.
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }

    /**
     * Updates the displayed score.
     *
     * @param score Display current score.
     */
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score); 
    }

    /**
     * Adds the score label to the root for display.
     */
    public void showScoreDisplay() {
        if (!root.getChildren().contains(scoreLabel)) {
            root.getChildren().add(scoreLabel);
        }
    }

    /**
     * Updates the heart display.
     *
     * @param hearts The number of hearts to display.
     */
    public void updateHeartDisplay(int hearts) {
        heartDisplay.updateHeartCount(hearts);
        System.out.println("Updated hearts to: " + hearts);
    }

    /**
     * Resets the heart display.
     *
     * @param heartsToDisplay The number of hearts to display after resetting.
     */
     public void resetHearts(int heartsToDisplay) {
        heartDisplay.resetHearts(heartsToDisplay);
        System.out.println("Hearts reset to: " + heartsToDisplay);
    }
}

