package com.example.demo.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PauseScreen {

    private Scene pauseScene;
    private final Stage stage;
    private final Scene mainScene; 
    private final Runnable settings;
    private final Runnable resume;
    private final Controller controller;

    public PauseScreen(Stage stage, Controller controller , Runnable resumeAction, Runnable settingsAction) {
        this.stage = stage;
        this.mainScene = stage.getScene();
        this.controller = controller;
        this.settings = settingsAction;
        this.resume = resumeAction;
        
    }

    public void show(){

        //Title Label
        Label titleLabel = new Label("Game Paused");
        titleLabel.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        titleLabel.setAlignment(Pos.CENTER);

        Stage pauseStage = new Stage();
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.setTitle("Game Pause");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        //Resume button
        Button resumeButton = new Button("Resume");
        resumeButton.setPrefWidth(200);
        resumeButton.setOnAction(event -> {
            if (resume != null){
                resume.run();
            }
            pauseStage.close();
        });

        //Settings button
        Button settingsButton = new Button("Settings");
        settingsButton.setPrefWidth(200);
        settingsButton.setOnAction(event -> {
            if (settings != null){
                settings.run();
                showSettings();
            }
        });

        //Exit button
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(200);
        exitButton.setOnAction(event -> {
            System.out.println("Exit game");
            pauseStage.close();
            stage.close();

        });

        layout.getChildren().addAll(titleLabel,resumeButton, settingsButton,exitButton);
        
        Scene pausesceneBox = new Scene(layout, 400, 250);
        pauseStage.setScene(pausesceneBox);
        //Show the pause box until it is closed
        pauseStage.showAndWait();

    }

    //Setting screen
    public void showSettings(){
        //Create new screen for setting
        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.setTitle("Settings");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label settingsLabel = new Label("Settings");
        settingsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        //Volume
        Label volumeLabel = new Label("Volume");
        volumeLabel.setStyle("-fx-font-size: 16px;");

        //Slider for volume which have the range of 0 to 1
        Slider volumeSlider = new Slider(0, 1, controller.getMediaPlayer().getVolume());
        //Show the label for tick marks
        volumeSlider.setShowTickLabels(true);
        //Show the tick marks with the slider
        volumeSlider.setShowTickMarks(true);
        //Increment value for the slider 
        volumeSlider.setBlockIncrement(0.1);
        //Update the volume with the slider
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> 
            controller.setVolume(newValue.doubleValue())
    );

        layout.getChildren().addAll(settingsLabel, volumeLabel, volumeSlider);
        //Create new scene for setting
        Scene settingsScene = new Scene(layout, 300, 200);
        settingsStage.setScene(settingsScene);
        settingsStage.showAndWait();
    }
}

   