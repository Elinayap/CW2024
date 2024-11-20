package com.example.demo.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.lang.reflect.InvocationTargetException;

public class MainMenu {

    private final Stage stage;
    private final Controller controller;
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/nature.jpg";
    private static final String PIXEL_FONT = "/com/example/demo/fonts/pixelFont.ttf";
    private static final String MOUSE_ICON = "/com/example/demo/images/mouse_icon.png";
    private static final String TRIANGLE_MOUSE_ICON = "/com/example/demo/images/triangle_mouse.png";

    private final ImageCursor hoverCursor;
    private final ImageCursor mouseCursor;


    public MainMenu(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;

        //Load the custom cursor from the path
        Image cursorMouse = new Image(this.getClass().getResource(MOUSE_ICON).toExternalForm());
        this.hoverCursor = new ImageCursor(cursorMouse);

        //Load the custom cursor from the path
        Image cursorMouseT = new Image(this.getClass().getResource(TRIANGLE_MOUSE_ICON).toExternalForm());
        this.mouseCursor = new ImageCursor(cursorMouseT);
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
        startGameButton.setOnMouseEntered(event -> startGameButton.setCursor(hoverCursor));
        //Add custom cursor for mouse cursor
        startGameButton.setOnMouseExited(event -> startGameButton.setCursor(mouseCursor));
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
        instructionsButton.setOnMouseEntered(event -> instructionsButton.setCursor(hoverCursor));
        //Add custom cursor for mouse cursor
        instructionsButton.setOnMouseExited(event -> instructionsButton.setCursor(mouseCursor));
        instructionsButton.setOnAction(event -> UserInstructions());

        //Quit Game Button
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
        quitGameButton.setOnMouseEntered(event -> quitGameButton.setCursor(hoverCursor));
        //Add custom cursor for mouse cursor
        quitGameButton.setOnMouseExited(event -> quitGameButton.setCursor(mouseCursor));
        quitGameButton.setOnAction(event -> stage.close());

        //Add components to the VBox
        vBox.getChildren().addAll(titleLabel, startGameButton, instructionsButton, quitGameButton);

        // Create Scene
        Scene scene = new Scene(vBox, 800, 600);
        //Set the triangle cursor to the screen when not hovering
        scene.setCursor(mouseCursor);
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
        //Create a box for instructions
        Stage instructionsStage = new Stage();
        instructionsStage.initModality(Modality.APPLICATION_MODAL);
        //Remove default box
        instructionsStage.initStyle(StageStyle.TRANSPARENT); 
    
        //Create BorderPane for pause screen
        BorderPane rootLayout = new BorderPane();
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image(this.getClass().getResource("/com/example/demo/images/pause.png").toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            //Ensure it fill the entire screen
            new BackgroundSize(1.0, 1.0, true, true, false, false) 
        );
        rootLayout.setBackground(new Background(backgroundImage));
    
        //Top section with custom close button
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(10));
        //Create a close button for instructions screen
        Button closeButton = new Button();
        closeButton.setStyle(
            "-fx-background-color: transparent; -fx-border-width: 0;"+
            "-fx-background-size: 50px 40px;"
        );
        //Set the custom image for button
        ImageView Closebutton = new ImageView(
        new Image(this.getClass().getResource("/com/example/demo/images/x_button.png").toExternalForm())
        );
        //Resize button X
        Closebutton.setFitWidth(30);
        Closebutton.setFitHeight(30);
        //Set resized image as graphic
        closeButton.setGraphic(Closebutton);
        
        closeButton.setOnAction(event -> instructionsStage.close());
        topBar.getChildren().add(closeButton);
    
        //To center the content
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        Label instructionLabel = new Label("In Sky Battle, your mission is to defend yourself from incoming bullets.\n\n" +
                "• Use arrow keys to navigate your aircraft.\n" +
                "• Press spacebar to fire your weapons.\n\n" +
                "Survive and defeat the boss to win. Good luck!");
             
        instructionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;"); 
        instructionLabel.setWrapText(true);
    
        content.getChildren().add(instructionLabel);
    
        //Add top bar to the root layout
        rootLayout.setTop(topBar);
        rootLayout.setCenter(content);
    
        //Create and set the scene
        Scene instructionsScene = new Scene(rootLayout, 400, 250);
        instructionsScene.setFill(null);
        instructionsScene.setCursor(mouseCursor);
        instructionsStage.setScene(instructionsScene);
        instructionsStage.show();
    }
}
