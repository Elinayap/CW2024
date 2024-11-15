package com.example.demo.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class MainMenu {

    private final Stage stage;
    private final Controller controller;
    private MediaPlayer mediaPlayer;
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background4.png";

    public MainMenu(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
        PlayMusic();
    }

    //Play background music
    private void PlayMusic() {
        try {
            //getResource to make sure to locate the path correctly
            URL resource = getClass().getResource("/com/example/demo/audios/movement.mp3");
            //Check if the file is found. If not, an error message is printed
            if (resource == null) {
                System.err.println("Media file not found at /com/example/demo/audios/movement.mp3");
                return;
            }
            //Print external form of URL to make sure the path is correct
            System.out.println("Media file found: " + resource.toExternalForm());
            //Create media object from the audio file
            Media sound = new Media(resource.toExternalForm());
            //Manage playback of media
            mediaPlayer = new MediaPlayer(sound);
            //Loop the music
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
            //Start playing music
            mediaPlayer.play(); 
            System.out.println("MediaPlayer initialized and playing.");
        } catch (Exception e) {
            System.err.println("Error initializing MediaPlayer:");
            e.printStackTrace();
        }
    }

    public void show() {
        
        //VBox to ensure it is center alignment
        VBox vBox = new VBox(30); 
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER); 

         //Set the background image
        BackgroundImage backgroundImage = new BackgroundImage(
                //Load background image from path
                new Image(this.getClass().getResource(BACKGROUND_IMAGE_NAME).toExternalForm()),
                //Set background to not repeat horizontally
                BackgroundRepeat.NO_REPEAT, 
                //Set background to not repeat vertically
                BackgroundRepeat.NO_REPEAT, 
                //Set background to center
                BackgroundPosition.CENTER, 
                //A
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        vBox.setBackground(new Background(backgroundImage));

        //Title Label
        Label titleLabel = new Label("Sky Battle");
        titleLabel.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        titleLabel.setAlignment(Pos.CENTER);

        //Exit full screen label
        Label exitFullScreenLabel = new Label("Press ESC to exit full-screen mode.");
        exitFullScreenLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: gray; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");

        //Start Game Button
        Button startGameButton = new Button("Start Game");
        startGameButton.setPrefWidth(200);
        startGameButton.setStyle("-fx-font-size: 16px;");
        startGameButton.setOnAction(event -> startGame());

        //Instructions button
        Button instructionsButton = new Button("Instructions");
        instructionsButton.setPrefWidth(200);
        instructionsButton.setStyle("-fx-font-size: 16px;");
        instructionsButton.setOnAction(event -> UserInstructions());

        //Volume slider with label
        Label volumeLabel = new Label("Volume:");
        volumeLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 14px;");
        Slider volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setMajorTickUnit(25);
        volumeSlider.setPrefWidth(200);

        //Connect the slider to the media
        if (mediaPlayer != null) {
            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                mediaPlayer.setVolume(newValue.doubleValue() / 100.0); // Convert to 0.0 - 1.0 range
            });
        } else {
            System.err.println("MediaPlayer is not initialized. Volume slider will not control music volume.");
        }

        //Volume layout
        HBox volumeBox = new HBox(10, volumeLabel, volumeSlider); 
        volumeBox.setAlignment(Pos.CENTER);

        //Add to the vBox
        vBox.getChildren().addAll(titleLabel, startGameButton, instructionsButton, volumeBox);

        //Create Scene 
        Scene scene = new Scene(vBox, 800, 600); 

        stage.setScene(scene);
        stage.setFullScreen(true); 
        stage.show();

    }

    private void startGame() {
        System.out.println("Starting the game...");
        try {
            controller.launchGame();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();  //Print errors for debugging
        }
    }

    private void UserInstructions() {
        //Create a box for instructions
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
