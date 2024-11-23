package com.example.demo.levels;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.UserPlane;
import com.example.demo.destructible.ActiveActorDestructible;
import com.example.demo.UI.GameEndScreen;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class LevelParent extends Observable {

    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;

    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;

    private boolean isUpdated = false;
    private boolean isChangedState = false;
    protected boolean isGameOver = false;
	private boolean isGameEndScreenTriggered = false;

    private final Group root;
    private final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;
    private final ImageView background;
    private boolean isPaused = false;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private int currentNumberOfEnemies;
    private LevelView levelView;
    private final Stage gameStage;

    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Stage gameStage) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.isUpdated = false;
        this.isChangedState = false;
        this.gameStage = gameStage;

        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        initializeTimeline();
        friendlyUnits.add(user);
    }

    protected abstract void initializeFriendlyUnits();

    //protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits();

    protected abstract LevelView instantiateLevelView();

    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        return scene;
    }

    public void startGame() {
        background.requestFocus();
        timeline.play();
    }

    public void pauseGame() {
        if (!isPaused) {
            timeline.pause();
            isPaused = true;
        }
    }

    public void resumeGame() {
        if (isPaused) {
            timeline.play();
            isPaused = false;
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void goToNextLevel(String levelName) {
        if (!isUpdated) {
            setChanged();
            notifyObservers(levelName);
            isUpdated = true;
            isChangedState = true;
        }
    }

    public boolean isChangedState() {
        return isChangedState;
    }

    protected void updateScene() {
        if (!isPaused && !isTransitioning) {
            spawnEnemyUnits();
            updateActors();
            generateEnemyFire();
            updateNumberOfEnemies();
            handleEnemyPenetration();
            handleUserProjectileCollisions();
            handleEnemyProjectileCollisions();
            handlePlaneCollisions();
            removeAllDestroyedActors();
            updateKillCount();
            updateLevelView();
            checkIfGameOver();
        }
    }

    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
	
		background.setOnKeyPressed(event -> {
			if (isGameOver && event.getCode() != KeyCode.TAB) {
				System.out.println("Key press blocked: Game is already over.");
				return;
			}
			if (!isGameOver) {
				KeyCode kc = event.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.LEFT) user.moveLeft();
				if (kc == KeyCode.RIGHT) user.moveRight();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});

		background.setOnKeyReleased(event -> {
			if (isGameOver || isTransitioning) {
				return;
			}
			if (!isPaused) {
				KeyCode kc = event.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) {
					user.stopVerticalMove();
				}
				if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) {
					user.stopHorizontalMove();
				}
			}
		});
		
	
		root.getChildren().add(background);
	}
	
	
    private void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }

    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    private void updateActors() {
        friendlyUnits.forEach(plane -> plane.updateActor());
        enemyUnits.forEach(enemy -> enemy.updateActor());
        userProjectiles.forEach(projectile -> projectile.updateActor());
        enemyProjectiles.forEach(projectile -> projectile.updateActor());
    }

    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
    }

    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    protected void winGame() {
        timeline.stop();
        levelView.showWinImage();
    }

	private boolean isTransitioning = false;

    protected void loseGame() {
		if (isGameOver || isTransitioning) {
			System.out.println("Game over already triggered or transitioning. Skipping...");
			return;
		}
	
		System.out.println("Game over triggered!");
		isGameOver = true;
		isTransitioning = true; // Set transitioning state
	
		timeline.stop(); // Stop all animations
		levelView.showGameOverImage(); // Display the "Game Over" image
	
		// Disable player input except for the Tab key
		getScene().setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.TAB && !isGameEndScreenTriggered) {
				System.out.println("Tab key pressed, showing Game End Screen...");
				isGameEndScreenTriggered = true; // Prevent multiple triggers
				transitionToGameEndScreen(); // Show the Game End Screen
			}
		});
	}
	
	private void transitionToGameEndScreen() {
		PauseTransition delay = new PauseTransition(Duration.seconds(1)); // Optional delay
		delay.setOnFinished(event -> {
			System.out.println("Transitioning to GameEndScreen...");
			int finalScore = getUser().getKillCount(); // Fetch the final score
			GameEndScreen.showGameEndScreen(gameStage, finalScore);
			isTransitioning = false; // Reset transitioning state
		});
		delay.play();
	}
	
	
	
		
	
	protected void checkIfGameOver() {
		if (isGameOver || isTransitioning) {
			System.out.println("Game over already triggered or transitioning. Skipping check.");
			return; // Prevent further execution if game is already over or transitioning
		}
	
		if (userIsDestroyed()) {
			System.out.println("User destroyed, triggering game over...");
			loseGame(); // Trigger the game-over logic
		}
	}
	
	

    protected UserPlane getUser() {
        return user;
    }

    protected Group getRoot() {
        return root;
    }

    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    protected double getScreenWidth() {
        return screenWidth;
    }

    protected boolean userIsDestroyed() {
        return getUser().getHealth() <= 0;
    }

    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }
}
