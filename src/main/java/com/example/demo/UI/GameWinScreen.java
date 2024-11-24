package com.example.demo.UI;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameWinScreen {

    private static boolean isGameWinScreenVisible = false;
    private static Stage gameWinStage = null;

    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/pause.png";
    private static final String PIXEL_FONT = "/com/example/demo/fonts/pixelFont.ttf";

    private GameWinScreen() {
        
    }

    public static void showGameWinScreen(Stage displayStage, int score,Runnable onNextLevel) {
        if (isGameWinScreenVisible) {
            return;
        }

        isGameWinScreenVisible = true;

        Platform.runLater(() -> {
            try {
                gameWinStage = new Stage();
                gameWinStage.initModality(Modality.APPLICATION_MODAL);
                gameWinStage.initStyle(StageStyle.TRANSPARENT);
                gameWinStage.initOwner(displayStage);

                BorderPane rootLayout = new BorderPane();

                // Set background image
                try {
                    BackgroundImage backgroundImage = new BackgroundImage(
                        new Image(GameWinScreen.class.getResource(BACKGROUND_IMAGE).toExternalForm()),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(1.0, 1.0, true, true, false, false)
                    );
                    rootLayout.setBackground(new Background(backgroundImage));
                } catch (Exception e) {
                    System.out.println("Error loading background image: " + e.getMessage());
                }

                VBox layout = new VBox(20);
                layout.setAlignment(Pos.CENTER);
                layout.setPadding(Insets.EMPTY);
                rootLayout.setPadding(Insets.EMPTY);

                Font titleFont;
                Font buttonFont;
                try {
                    titleFont = Font.loadFont(GameWinScreen.class.getResource(PIXEL_FONT).toExternalForm(), 40);
                    buttonFont = Font.loadFont(GameWinScreen.class.getResource(PIXEL_FONT).toExternalForm(), 20);
                } catch (Exception e) {
                    System.out.println("Error loading font: " + e.getMessage());
                    titleFont = Font.font("Arial", 40);
                    buttonFont = Font.font("Arial", 20);
                }

                Label titleLabel = new Label("You Win!");
                titleLabel.setFont(titleFont);
                titleLabel.setStyle("-fx-text-fill: #000000;");

                Label scoreLabel = new Label("Your Score: " + score);
                scoreLabel.setFont(buttonFont);
                scoreLabel.setStyle("-fx-text-fill: #000000;");

                Button playAgainButton = new Button("Play Again");
                playAgainButton.setFont(buttonFont);
                playAgainButton.setPrefWidth(200);
                playAgainButton.setPrefHeight(50);
                playAgainButton.setStyle(
                    "-fx-background-image: url('" + GameWinScreen.class.getResource("/com/example/demo/images/grass_button.png").toExternalForm() + "');" +
                    "-fx-background-size: 100% 100%;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-text-fill: #000000;" +
                    "-fx-alignment: center;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-width: 0;"
                );
                playAgainButton.setOnAction(event -> {
                    gameWinStage.close();
                    resetGame();
                });

                Button nextLevelButton = new Button("Go to Next Level");
                nextLevelButton.setFont(buttonFont);
                nextLevelButton.setPrefWidth(200);
                nextLevelButton.setPrefHeight(50);
                nextLevelButton.setStyle(
                    "-fx-background-image: url('" + GameWinScreen.class.getResource("/com/example/demo/images/grass_button.png").toExternalForm() + "');" +
                    "-fx-background-size: 100% 100%;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-text-fill: #000000;" +
                    "-fx-alignment: center;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-width: 0;"
                );
                nextLevelButton.setOnAction(event -> {
                    gameWinStage.close();
                    isGameWinScreenVisible = false;
                    //Go to next level
                    onNextLevel.run();
                });

                Button mainMenuButton = new Button("Return to Menu");
                mainMenuButton.setFont(buttonFont);
                mainMenuButton.setPrefWidth(200);
                mainMenuButton.setPrefHeight(50);
                mainMenuButton.setStyle(
                    "-fx-background-image: url('" + GameWinScreen.class.getResource("/com/example/demo/images/grass_button.png").toExternalForm() + "');" +
                    "-fx-background-size: 100% 100%;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-text-fill: #000000;" +
                    "-fx-alignment: center;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-width: 0;"
                );
                mainMenuButton.setOnAction(event -> {
                    gameWinStage.close();
                });

                layout.getChildren().addAll(titleLabel, scoreLabel, nextLevelButton, playAgainButton, mainMenuButton);
                rootLayout.setCenter(layout);

                Scene gameWinScene = new Scene(rootLayout, 500, 500);
                //Make the scene transparent
                gameWinScene.setFill(null); 
                gameWinStage.setScene(gameWinScene);

                gameWinStage.setOnHidden(event -> isGameWinScreenVisible = false);

                gameWinStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void showlvl3WinScreen(Stage displayStage, int score) {
        if (isGameWinScreenVisible) {
            return;
        }
    
        isGameWinScreenVisible = true;
    
        Platform.runLater(() -> {
            try {
                gameWinStage = new Stage();
                gameWinStage.initModality(Modality.APPLICATION_MODAL);
                gameWinStage.initStyle(StageStyle.TRANSPARENT);
                gameWinStage.initOwner(displayStage);
    
                BorderPane rootLayout = new BorderPane();
    
                //Set background image
                try {
                    BackgroundImage backgroundImage = new BackgroundImage(
                        new Image(GameWinScreen.class.getResource(BACKGROUND_IMAGE).toExternalForm()),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(1.0, 1.0, true, true, false, false)
                    );
                    rootLayout.setBackground(new Background(backgroundImage));
                } catch (Exception e) {
                    System.out.println("Error loading background image: " + e.getMessage());
                }
    
                VBox layout = new VBox(20);
                layout.setAlignment(Pos.CENTER);
                layout.setPadding(Insets.EMPTY);
                rootLayout.setPadding(Insets.EMPTY);
    
                Font titleFont;
                Font buttonFont;
                try {
                    titleFont = Font.loadFont(GameWinScreen.class.getResource(PIXEL_FONT).toExternalForm(), 40);
                    buttonFont = Font.loadFont(GameWinScreen.class.getResource(PIXEL_FONT).toExternalForm(), 20);
                } catch (Exception e) {
                    System.out.println("Error loading font: " + e.getMessage());
                    titleFont = Font.font("Arial", 40);
                    buttonFont = Font.font("Arial", 20);
                }
    
                Label titleLabel = new Label("You Win!");
                titleLabel.setFont(titleFont);
                titleLabel.setStyle("-fx-text-fill: #000000;");
    
                Label scoreLabel = new Label("Your Score: " + score);
                scoreLabel.setFont(buttonFont);
                scoreLabel.setStyle("-fx-text-fill: #000000;");
    
                Button mainMenuButton = new Button("Return to Menu");
                mainMenuButton.setFont(buttonFont);
                mainMenuButton.setPrefWidth(200);
                mainMenuButton.setPrefHeight(50);
                mainMenuButton.setStyle(
                    "-fx-background-image: url('" + GameWinScreen.class.getResource("/com/example/demo/images/grass_button.png").toExternalForm() + "');" +
                    "-fx-background-size: 100% 100%;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-text-fill: #000000;" +
                    "-fx-alignment: center;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-width: 0;"
                );
                mainMenuButton.setOnAction(event -> {
                    gameWinStage.close();
                });
    
                layout.getChildren().addAll(titleLabel, scoreLabel, mainMenuButton);
                rootLayout.setCenter(layout);
    
                Scene gameWinScene = new Scene(rootLayout, 500, 500);
                //Make the scene transparent
                gameWinScene.setFill(null); 
                gameWinStage.setScene(gameWinScene);
    
                gameWinStage.setOnHidden(event -> isGameWinScreenVisible = false);
    
                gameWinStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    

    private static void resetGame() {
        System.out.println("Play again");
        isGameWinScreenVisible = false; 
    }
}
