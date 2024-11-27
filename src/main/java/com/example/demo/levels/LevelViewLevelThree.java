package com.example.demo.levels;

import com.example.demo.assets.ShieldImage;
import javafx.scene.Group;

public class LevelViewLevelThree extends LevelView {

    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;
    private final ShieldImage shieldImage;

    public LevelViewLevelThree(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay); // Call the superclass constructor
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        addImagesToRoot(root);
    }

    private void addImagesToRoot(Group root) {
        root.getChildren().addAll(shieldImage);
    }

    public void showShield() {
        shieldImage.showShield();
    }

    public void hideShield() {
        shieldImage.hideShield();
    }
}
