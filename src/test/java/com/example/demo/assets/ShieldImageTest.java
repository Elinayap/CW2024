package com.example.demo.assets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;

/**
 * Junit tests for the {@link ShieldImage} class.
 */
public class ShieldImageTest {

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
     * Tests if the shield image is displayed.
     * Ensures that the shield is visible after {@link ShieldImage#showShield()} method is called.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
     @Test
    void testShowShield() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                ShieldImage shieldImage = new ShieldImage(0, 0);
                shieldImage.showShield();
                assertTrue(shieldImage.isVisible());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests if the shield image is hidden.
     * Ensures that the shield is hidden after {@link ShieldImage#hideShield()} method is called.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
    @Test
    void testHideShield() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                ShieldImage shieldImage = new ShieldImage(0, 0);
                shieldImage.hideShield();
                assertFalse(shieldImage.isVisible());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }
}
