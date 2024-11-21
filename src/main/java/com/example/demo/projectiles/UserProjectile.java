package com.example.demo.projectiles;

import java.net.URL;
import javafx.scene.media.AudioClip;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;
	private static final String SHOOTING_SOUND = "/com/example/demo/audios/shooting.wav";
	private static AudioClip shootingSound;

	static{

		try{
			URL soundURL = UserProjectile.class.getResource(SHOOTING_SOUND);
			if (soundURL == null){
				System.err.println("Fail to load sound");
			} else{
				shootingSound = new AudioClip(soundURL.toExternalForm());
				System.out.println("Success to load sound");
			}
		} catch (Exception e){
			System.err.println("Fail to load sound"+ e.getMessage());
			shootingSound = null;
		}
	}

	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);

		playSound();
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	

	private void playSound(){
		if (shootingSound != null){
			shootingSound.play();
		}
	}
	
}

