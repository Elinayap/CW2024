package com.example.demo.levels;

import com.example.demo.GameState.GameState;
import com.example.demo.actors.EnemyPlane;
import com.example.demo.destructible.ActiveActorDestructible;

import javafx.stage.Stage;

public class LevelOne extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 1;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int SCORE_TO_ADVANCE = 40;

    private final LevelView levelView;

    public LevelOne(double screenHeight, double screenWidth, Stage gameStage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, gameStage);
        this.levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected void checkIfGameOver() {
        if (isGameOver) {
            return;
        }
        if (userIsDestroyed()) {
            loseGame(); // Trigger game-over logic only when health is zero
        } else if (userHasReachedKillTarget() && !isTransitioning) {
            winGame(NEXT_LEVEL); // Trigger win condition only when user meets the kill target
        }
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    @Override
    public LevelView getLevelView() {
        return levelView; // Return the LevelView instance
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE 
            && getPlayerScore() >= SCORE_TO_ADVANCE 
            && !isGameOver;
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
}







