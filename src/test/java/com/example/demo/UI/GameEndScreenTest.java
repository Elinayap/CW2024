package com.example.demo.UI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.stage.Stage;

public class GameEndScreenTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Display GameEndScreen
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

    // Score display
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
