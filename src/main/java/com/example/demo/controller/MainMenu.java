package com.example.demo.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class MainMenu {

    private final Stage stage;
    private final Controller controller;
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.png";
    private static final String PIXEL_FONT = "/com/example/demo/fonts/pixelFont.ttf";
    private static final String MOUSE_ICON = "/com/example/demo/images/mouse_icon.png";

    private final ImageCursor customCursor;

    public MainMenu(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;

        //Load the custom cursor from the path
        Image cursorMouse = new Image(this.getClass().getResource(MOUSE_ICON).toExternalForm());
        this.customCursor = new ImageCursor(cursorMouse);
    }

    public void show() {

        //VBox for center alignment
        VBox vBox = new VBox(30);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);

        //Set the background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(this.getClass().getResource(BACKGROUND_IMAGE_NAME).toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        vBox.setBackground(new Background(backgroundImage));

        //Load the custom pixel font for title only
        Font titleFont = Font.loadFont(this.getClass().getResource(PIXEL_FONT).toExternalForm(), 48);

        //Title Label with custom font
        Label titleLabel = new Label("Sky Battle");
        if (titleFont != null) {
            titleLabel.setFont(titleFont); 
        } else {
            System.err.println("Error: Pixel font could not be loaded.");
        }
        titleLabel.setStyle(
            "-fx-text-fill: #000000;"
            
            
            ); 
        titleLabel.setAlignment(Pos.CENTER);

        //Load the custom pixel font for the buttons
        Font buttonFont = Font.loadFont(this.getClass().getResource(PIXEL_FONT).toExternalForm(), 20);

        //Start Game Button
        Button startGameButton = new Button("");
        startGameButton.setPrefWidth(200);
        startGameButton.setPrefHeight(50);
        startGameButton.setStyle(
            "-fx-background-image: url('" + getClass().getResource("/com/example/demo/images/play_button.png").toExternalForm() + "');" +
            "-fx-background-size: 100% 100%;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-text-fill: white;" +
            "-fx-alignment: center;" +
            "-fx-background-color: transparent;" +
            "-fx-border-width: 0;"            
        );
        //Add custom cursor for hover
        startGameButton.setOnMouseEntered(event -> startGameButton.setCursor(customCursor));
        startGameButton.setOnMouseExited(event -> startGameButton.setCursor(null));
        startGameButton.setOnAction(event -> startGame());

        //Instructions button with custom font
        Button instructionsButton = new Button("Instructions");
        if (buttonFont != null) {
            instructionsButton.setFont(buttonFont); 
        }
        instructionsButton.setPrefWidth(200);
        instructionsButton.setPrefHeight(50);
        instructionsButton.setStyle(
            "-fx-background-image: url('" + getClass().getResource("/com/example/demo/images/wood_button.png").toExternalForm() + "');" +
            "-fx-background-size: 100% 100%;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-text-fill: #A67C52;" +
            "-fx-alignment: center;" +
            "-fx-background-color: transparent;" +
            "-fx-border-width: 0;"
        );
        //Add custom cursor for hover
        Image cursorImage = new Image(this.getClass().getResource("/com/example/demo/images/mouse_icon.png").toExternalForm());
        instructionsButton.setCursor(new ImageCursor(cursorImage));
        instructionsButton.setOnAction(event -> UserInstructions());

        // Quit Game Button
        Button quitGameButton = new Button("Quit Game");
        if (buttonFont != null) {
            quitGameButton.setFont(buttonFont); 
        }
        quitGameButton.setPrefWidth(200);
        quitGameButton.setPrefHeight(50);
        quitGameButton.setStyle(
            "-fx-background-image: url('" + getClass().getResource("/com/example/demo/images/wood_button.png").toExternalForm() + "');" +
            "-fx-background-size: 100% 100%;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-text-fill: #A67C52;" +
            "-fx-alignment: center;" +
            "-fx-background-color: transparent;" +
            "-fx-border-width: 0;"
        );
        //Add custom cursor for hover
        quitGameButton.setOnMouseEntered(event -> quitGameButton.setCursor(customCursor));
        quitGameButton.setOnMouseExited(event -> quitGameButton.setCursor(null));
        quitGameButton.setOnAction(event -> stage.close());

        // Add components to the VBox
        vBox.getChildren().addAll(titleLabel, startGameButton, instructionsButton, quitGameButton);

        // Create Scene
        Scene scene = new Scene(vBox, 800, 600);

        stage.setScene(scene);
        stage.show();
    }

    private void startGame() {
        System.out.println("Starting the game");
        try {
            controller.launchGame();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //Print errors for debugging
        }
    }

    private void UserInstructions() {
        // Create a box for instructions
        Stage instructionsStage = new Stage();
        instructionsStage.initModality(Modality.APPLICATION_MODAL);
        instructionsStage.setTitle("Sky Battle Instructions");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label instructionLabel = new Label("In Sky Battle, your mission is to defend yourself from incoming bullets.\n\n" +
                "• Use arrow keys to navigate your aircraft.\n" +
                "• Press spacebar to fire your weapons.\n\n" +
                "Survive and defeat the boss to win. Good luck!");
        instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
        instructionLabel.setWrapText(true);

        layout.getChildren().add(instructionLabel);

        Scene instructionsScene = new Scene(layout, 400, 250);
        instructionsStage.setScene(instructionsScene);
        instructionsStage.show();
    }
}
