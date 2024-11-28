package com.example.demo.levels;

import com.example.demo.GameState.GameState;
import com.example.demo.actors.Boss;
import com.example.demo.assets.ShieldImage;

import javafx.stage.Stage;

public class LevelTwo extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;
    private static final int KILLS_TO_ADVANCE = 1;
    private LevelViewLevelTwo levelView;
    private ShieldImage shieldImage;
    public static final int SHIELD_SIZE = 200;

    public LevelTwo(double screenHeight, double screenWidth, Stage gameStage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth,  GameState.getInstance().getLevel2Hearts(), gameStage);

        //Initialize the image at (0,0)
        shieldImage = new ShieldImage(0, 0);
        boss = new Boss(shieldImage);
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

        ///Match the shield's Y position with the boss plane's Y position
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
        } else if (boss.isDestroyed()) {
            winGame(NEXT_LEVEL); 
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
        int updatedHearts = GameState.getInstance().getLevel2Hearts();
        return new LevelView(getRoot(), updatedHearts);
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
        //To show the shield image
        public void activateShield() {
            shieldImage.showShield();
            
        }

        //To hide the shield image
        public void deactivateShield() {
            shieldImage.hideShield();
        }

        private boolean userHasReachedKillTarget() {

            return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE && !isChangedState();
        }
        
        @Override
        public LevelView getLevelView() {
            return levelView;
        }

}
