package com.example.demo.levels;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.UserPlane;
import com.example.demo.destructible.ActiveActorDestructible;
import com.example.demo.UI.GameEndScreen;
import com.example.demo.UI.GameWinScreen;

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
	private int playerScore;
	

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
		this.playerScore = 0;
		

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
		levelView.updateScore(playerScore);
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
			resetScore();
        }
    }

    public boolean isChangedState() {
        return isChangedState;
    }

	private void resetScore() {
		playerScore = 0; // Reset score to 0
		levelView.updateScore(playerScore); // Update the score display
		System.out.println("Score reset to 0 for the next level.");
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
		List<ActiveActorDestructible> destroyedProjectiles = new ArrayList<>();
		List<ActiveActorDestructible> destroyedEnemies = new ArrayList<>();
	
		for (ActiveActorDestructible projectile : userProjectiles) {
			boolean projectileHit = false;
	
			for (ActiveActorDestructible enemy : enemyUnits) {
				
				if (projectile.getBoundsInParent().intersects(enemy.getBoundsInParent()) && !projectileHit) {
					projectile.takeDamage();
					enemy.takeDamage();
	
					// Add score only if the enemy is destroyed
					if (enemy.isDestroyed()) {
						destroyedEnemies.add(enemy); 
						if (this instanceof LevelOne) {
							addScore(40); //Add 40 points for LevelOne
						}
						if (this instanceof LevelTwo) {
							addScore(60); //Add 60 points for LevelTwo
						}
						if (this instanceof LevelThree) {
							addScore(100); //Add 100 points for LevelTwo
						}
						
					}
	
					destroyedProjectiles.add(projectile); 
					projectileHit = true; 
				}
			}
		}
	
		// Cleanup: Remove destroyed projectiles and enemies
		root.getChildren().removeAll(destroyedProjectiles);
		userProjectiles.removeAll(destroyedProjectiles);
		root.getChildren().removeAll(destroyedEnemies);
		enemyUnits.removeAll(destroyedEnemies);
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
		for (ActiveActorDestructible enemy : new ArrayList<>(enemyUnits)) {
			if (enemyHasPenetratedDefenses(enemy)) {
				System.out.println("Enemy exited the screen! Deducting all hearts.");
	
				// Deduct all hearts from the user
				while (user.getHealth() > 0) {
					user.takeDamage();
				}
	
				// Remove the enemy and trigger game over
				enemy.destroy();
				root.getChildren().remove(enemy);
				enemyUnits.remove(enemy);
	
				// Trigger game over logic
				if (userIsDestroyed()) {
					loseGame();
				}
			}
		}
	}
	
	
    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
		levelView.updateScore(playerScore);
    }

    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return enemy.getBoundsInParent().getMaxX() < 0 || // Off-screen to the left
			   enemy.getBoundsInParent().getMinX() > screenWidth || // Off-screen to the right
			   enemy.getBoundsInParent().getMaxY() < 0 || // Off-screen above
			   enemy.getBoundsInParent().getMinY() > screenHeight; // Off-screen below
	}
	
	

	private void addScore(int points) {
        playerScore += points;
        System.out.println("Score: " + playerScore);
    }


    protected void winGame(String nextLevel) {
		if (isGameOver) return;
		timeline.stop();
		isGameOver = true;
	
		if (nextLevel == null) {
			//Remove the "go to next level" button for level 3
			GameWinScreen.showlvl3WinScreen(gameStage, playerScore);
		} else {
			//Show the "go to next level" button
			GameWinScreen.showGameWinScreen(gameStage, playerScore, () -> goToNextLevel(nextLevel));
		}
	}
	private boolean isTransitioning = false;

	protected void loseGame() {
        if (isGameOver) return;
        timeline.stop();
        isGameOver = true;
        GameEndScreen.showGameEndScreen(gameStage, playerScore);
    }
		
	
	protected void checkIfGameOver() {
		if (isGameOver || isTransitioning) {
			return;
		}
	
		if (userIsDestroyed()) {
			loseGame(); 
		} else if (allEnemiesDefeated()) { 
			//Go to next level
			winGame("com.example.demo.levels.LevelTwo"); 
		}
	}
	

	private boolean allEnemiesDefeated() {
		return enemyUnits.isEmpty();
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



    private void updateScoreDisplay() {
        // If you have a LevelView or score label, update it here
        System.out.println("Player Score Updated: " + playerScore);
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
