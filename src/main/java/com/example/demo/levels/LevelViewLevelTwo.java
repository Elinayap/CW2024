package com.example.demo.levels;

import com.example.demo.assets.HeartDisplay;
import com.example.demo.assets.ShieldImage;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;
    private final Group root;
    private final ShieldImage shieldImage;
    private final HeartDisplay heartDisplay;
    private int hearts;

    public LevelViewLevelTwo(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        this.heartDisplay = new HeartDisplay(20, 20, heartsToDisplay); 
        addImagesToRoot();
        addHeartDisplayToRoot();
    }

    private void addImagesToRoot() {
        root.getChildren().addAll(shieldImage);
    }

    private void addHeartDisplayToRoot() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    public void showShield() {
        shieldImage.showShield();
    }

    public void hideShield() {
        shieldImage.hideShield();
    }

    @Override
    public void updateHeartDisplay(int hearts) {
        this.hearts = hearts;
        System.out.println("Heart display updated: " + hearts);
    }

    
}


