package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

public class Controller implements Observer {  //change observer(leave it)

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
	private final Stage stage;
	private Scene mainScene;
	private LevelParent currentLevel;
	private boolean isGamePaused = false;

	public Controller(Stage stage) {
		this.stage = stage;
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
        PauseScreen pauseScreen = new PauseScreen(stage, this::resumeGame, this::openSettings);
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