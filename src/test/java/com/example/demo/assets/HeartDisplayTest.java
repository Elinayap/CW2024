package com.example.demo.assets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;

/**
 * Junit tests for  {@link HeartDisplay} class.
 */
public class HeartDisplayTest {

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
     * Tests the removing heart from the {@link HeartDisplay} method.
     * Ensures that the heart count is decremented by 1 correctly.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
     @Test
    void testRemoveHeart() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                HeartDisplay heartDisplay = new HeartDisplay(0, 0, 5);
                heartDisplay.removeHeart();
                //Initially 5 then decrement by 1
                //Expected should be 4
                assertEquals(4, heartDisplay.getContainer().getChildren().size());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the adding heart from the {@link HeartDisplay} method.
     * Ensures that the heart count is incremented by 1 correctly.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
    @Test
    void testAddHeart() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                HeartDisplay heartDisplay = new HeartDisplay(0, 0, 4);
                heartDisplay.addHeart();

                assertEquals(5, heartDisplay.getContainer().getChildren().size());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the resetting heart from the {@link HeartDisplay} method.
     * Ensures that the heart count is the same as the default value.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
    @Test
    void testResetHearts() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                HeartDisplay heartDisplay = new HeartDisplay(0, 0, 4);
                heartDisplay.resetHearts(5);
                assertEquals(5, heartDisplay.getContainer().getChildren().size());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }
}
