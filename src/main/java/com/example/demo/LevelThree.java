package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class LevelThree extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/river.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private final List<bombImage> bombs;
	private LevelViewLevelThree levelView;
	private ShieldImage shieldImage;
	public static final int SHIELD_SIZE = 200;
	private static final double BOMB_PROBABILITY = 0.02;
	

	public LevelThree(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);

		//Initialize the image at (0,0)
    	shieldImage = new ShieldImage(0, 0);
		boss = new Boss(shieldImage);
		bombs = new ArrayList<>();

		//Adjust the position of the image
		double xOffset = 100;  
    	double yOffset = 100; 

    //Match the shield's X position with the boss plane's X position
    shieldImage.layoutXProperty().bind(
        boss.layoutXProperty()
        .add(boss.translateXProperty())
        .subtract(ShieldImage.SHIELD_SIZE / 2)
        .add(xOffset)
    );
	//Match the shield's Y position with the boss plane's Y position
    shieldImage.layoutYProperty().bind(
        boss.layoutYProperty()
        .add(boss.translateYProperty())
        .subtract(ShieldImage.SHIELD_SIZE / 2)
        .add(yOffset)
	);
		

	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		getRoot().getChildren().add(shieldImage);

	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelThree(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
	
	protected void updateScene() {
        super.updateScene();

		//Randomly spawn a bomb based on the probability
        if (Math.random() < BOMB_PROBABILITY) {
            spawnBomb();
        }
    }

	private void spawnBomb() {
        //Clear the existing bombs
        bombs.forEach(bomb -> getRoot().getChildren().remove(bomb));
        bombs.clear();

        //Spawn a bomb
        double x = Math.random() * (getScreenWidth() - bombImage.BOMB_SIZE);
        double y = Math.random() * (getEnemyMaximumYPosition() - bombImage.BOMB_SIZE);
        bombImage bomb = new bombImage(x, y);
        bomb.setVisible(true);
        bombs.add(bomb);
        getRoot().getChildren().add(bomb);

    }


	@Override
    public void startGame() {
        super.startGame();
	}
		//To show the shield image
		public void activateShield() {
			shieldImage.showShield();
			
		}

		//To hide the shield image
		public void deactivateShield() {
			shieldImage.hideShield();
		}
}
