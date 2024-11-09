package com.example.demo.controller;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameEndScreen {
    private final Stage displayStage;
    private final int score;
    private boolean isDisplayed;

    public GameEndScreen(Stage displayStage, int score) {
        this.displayStage = displayStage;
        this.score = score;
        this.isDisplayed = false;

        initializeUI();
    }

    private void initializeUI() {
        System.out.println("Attempting to initialize GameEndScreen...");  // Debug line
        if (isDisplayed) {
            System.out.println("GameEndScreen already displayed, skipping initialization.");  // Debug line
            return;
        }
        isDisplayed = true;

        Platform.runLater(() -> {
            System.out.println("Displaying GameEndScreen on JavaFX thread.");  // Debug line
            VBox layout = new VBox(15);
            layout.setAlignment(Pos.CENTER);

            Label finalScoreLabel = new Label("Your Score: " + score);
            finalScoreLabel.setStyle("-fx-font-size: 18px;");
            layout.getChildren().add(finalScoreLabel);

            Button restartButton = new Button("Restart");
            restartButton.setOnAction(event -> onRestartGame());
            layout.getChildren().add(restartButton);

            Button mainMenuButton = new Button("Return to menu");
            mainMenuButton.setOnAction(event -> returnToMainMenu());
            layout.getChildren().add(mainMenuButton);

            Scene scene = new Scene(layout, 300, 200);
            displayStage.setScene(scene);
            displayStage.show();
        });
    }

    private void onRestartGame() {
        System.out.println("Restarting the game...");
        isDisplayed = false;

    }

    private void returnToMainMenu() {
        System.out.println("Returning to main menu...");
        isDisplayed = false;
    }
}
