package com.example.demo.UI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Junit tests for the {@link GameEndScreen} class.
 */
public class GameEndScreenTest {

    /**
     * Sets up the JavaFX environment needed for testing.
     * Runs before all tests to start the JavaFX application thread.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    /**
     * Test if the GameEndScreen can be displayed.
     * 
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
    @Test
    void testShowGameEndScreen() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                assertDoesNotThrow(() -> GameEndScreen.showGameEndScreen(stage, 100));
            }   finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Test if the score is displayed on the GameEndScreen.
     * Ensures the displayed score does not have any runtime errors.
     * 
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
    @Test
    void testScoreDisplay() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                int score = 100;
                GameEndScreen.showGameEndScreen(stage, score);
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

}
