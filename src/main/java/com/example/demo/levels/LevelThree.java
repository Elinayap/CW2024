package com.example.demo.levels;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.GameState.GameState;
import com.example.demo.actors.Boss;
import com.example.demo.assets.ShieldImage;
import com.example.demo.assets.bombImage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/river.jpg";
    private static final String BOMB_COLLISION_SOUND = "/com/example/demo/audios/bomb.mp3";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;
    private final List<bombImage> bombs;
    private LevelView levelView;
    private ShieldImage shieldImage;
    public static final int SHIELD_SIZE = 200;
    private static final double BOMB_PROBABILITY = 0.02;

    public LevelThree(double screenHeight, double screenWidth, Stage gameStage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, gameStage);

        // Initialize the shield image
        shieldImage = new ShieldImage(0, 0);
        boss = new Boss(shieldImage);
        bombs = new ArrayList<>();

        // Initialize LevelView for consistency across all levels
        this.levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);

        // Adjust the position of the shield image
        double xOffset = 100;
        double yOffset = 100;

        // Bind shield position to boss position
        shieldImage.layoutXProperty().bind(
            boss.layoutXProperty()
                .add(boss.translateXProperty())
                .subtract(SHIELD_SIZE / 2)
                .add(xOffset)
        );

        shieldImage.layoutYProperty().bind(
            boss.layoutYProperty()
                .add(boss.translateYProperty())
                .subtract(SHIELD_SIZE / 2)
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
        } else if (boss.isDestroyed()) {
            winGame(null);
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }

    @Override
    public LevelView getLevelView() {
        return levelView; // Use the general LevelView class
    }

    @Override
    protected void updateScene() {
        super.updateScene();

        // Randomly spawn a bomb based on the probability
        if (Math.random() < BOMB_PROBABILITY) {
            spawnBomb();
        }

        bombCollision();
    }

    private void spawnBomb() {
        // Remove all existing bombs from the scene
        bombs.forEach(bomb -> getRoot().getChildren().remove(bomb));
        bombs.clear();

        // Spawn a new bomb
        double x = Math.random() * (getScreenWidth() - bombImage.BOMB_SIZE);
        double y = Math.random() * (getEnemyMaximumYPosition() - bombImage.BOMB_SIZE);
        bombImage bomb = new bombImage(x, y);
        bomb.setVisible(true);
        bombs.add(bomb);
        getRoot().getChildren().add(bomb);
    }

    private void bombCollision() {
        bombs.forEach(bomb -> {
            // Check if the bomb intersects with the user plane
            if (bomb.getBoundsInParent().intersects(getUser().getBoundsInParent())) {
                getUser().takeDamage();
                playBombSound(); 

                // Update level view hearts display
                if (levelView != null) {
                    levelView.removeHearts(getUser().getHealth());
                } else {
                    System.err.println("Error: levelView is null.");
                }

                bomb.setVisible(false);
                getRoot().getChildren().remove(bomb);
            }
        });
        bombs.removeIf(bomb -> !bomb.isVisible());
    }

    
    private void playBombSound() {
        try {
            Media bombSound = new Media(getClass().getResource(BOMB_COLLISION_SOUND).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(bombSound);
            mediaPlayer.play();
        } catch (NullPointerException e) {
            System.err.println("Bomb sound file not found: " + BOMB_COLLISION_SOUND);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
public void startGame() {
    super.startGame();
    if (levelView != null) {
        levelView.resetHearts(PLAYER_INITIAL_HEALTH); // Reset hearts to initial value
    }
     GameState.getInstance().setLevel1Hearts(PLAYER_INITIAL_HEALTH);
    System.out.println("Game started with hearts: " + PLAYER_INITIAL_HEALTH);
}


    public void activateShield() {
        shieldImage.showShield();
    }

    public void deactivateShield() {
        shieldImage.hideShield();
    }

    
}
