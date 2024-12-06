package com.example.demo.UI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.demo.levels.LevelParent;
import javafx.application.Platform;
import javafx.stage.Stage;

public class GameWinScreenTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Display Game Win Screen in LevelOne and LevelTwo
    @Test
    void testGameWinScreenDisplay() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelParent currentLevel = mock(LevelParent.class);
                Runnable mockNextLevelAction = mock(Runnable.class);

                assertDoesNotThrow(() -> GameWinScreen.showGameWinScreen(stage, 150, currentLevel, mockNextLevelAction));
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    // Next Level Button 
    @Test
    void testNextLevelButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelParent currentLevel = mock(LevelParent.class);
                Runnable mockNextLevelAction = mock(Runnable.class);

                GameWinScreen.showGameWinScreen(stage, 150, currentLevel, mockNextLevelAction);

                assertDoesNotThrow(() -> {
                    mockNextLevelAction.run();
                });
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }
      // Display LevelThree win screen
    @Test
    void testLevel3WinScreenDisplay() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                assertDoesNotThrow(() -> GameWinScreen.showlvl3WinScreen(stage, 100));
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

}
