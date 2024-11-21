package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import com.example.demo.levels.LevelParent;
import com.example.demo.UI.PauseScreen;
import com.example.demo.UI.MainMenu;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import com.example.demo.levels.LevelParent;

public class Controller implements Observer {  //change observer(leave it)

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;
	private Scene mainScene;
	private LevelParent currentLevel;
	private boolean isGamePaused = false;
	private MediaPlayer mediaPlayer;
	private double currentVolume = 0.5;


	public Controller(Stage stage) {
		this.stage = stage;
		PlayMusic();
	}

	//Play background music
	private void PlayMusic() {
		//Create media object from the audio file
        Media media = new Media(this.getClass().getResource("/com/example/demo/audios/movement.mp3").toExternalForm());
		 //Manage playback of media
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(currentVolume);
		//Loop the music
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		//Start playing music
        mediaPlayer.play();
    }
	
	//Retrieve the MediaPlayer
	public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

	//Control the volume of media player
	public void setVolume(double volume) {
        currentVolume = volume;
        mediaPlayer.setVolume(volume);
    }
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

			goToLevel(LEVEL_ONE_CLASS_NAME);


	}

	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Class<?> levelClass = Class.forName(className);
			Constructor<?> constructor = levelClass.getConstructor(double.class, double.class);
			currentLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
			currentLevel.addObserver(this);
			Scene scene = currentLevel.initializeScene();

			//Key handling event for pause screen
			scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
			stage.setScene(scene);
			currentLevel.startGame();
			
	}

	//Handle the key press event for ESCAPE key
	private void handleKeyPress(KeyCode code){
		if (code == KeyCode.ESCAPE){
			//Pause the game if it is not pause
			if (!isGamePaused){
				showPauseScreen();
			}
			else{
				resumeGame();
			}
		}
	}

	//Showing pause screen
	private void showPauseScreen() {
		//Set pause game flag
		isGamePaused = true;
		//Pause timeline in current level
		currentLevel.pauseGame();
		//Create pause screen and show the resume and settings options
        PauseScreen pauseScreen = new PauseScreen(stage, this, this::resumeGame, this::openSettings);
        pauseScreen.show();  //Show the pause screen
    }
	private void resumeGame() {
		//Clear the game flag
		isGamePaused = false;
		//Resume timeline in current level
		currentLevel.resumeGame();
		stage.setScene(currentLevel.getScene());
		
    }
	//Open settings screen
	private void openSettings() {
    
        System.out.println("Settings screen");
    }


	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

}
