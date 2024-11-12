package com.example.demo.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PauseScreen {

    private Scene pauseScene;
    private final Stage stage;
    private final Scene mainScene; 
    private final Runnable settings;
    private final Runnable resume;

    public PauseScreen(Stage stage, Runnable resumeAction, Runnable settingsAction) {
        this.stage = stage;
        this.mainScene = stage.getScene();
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
}

   