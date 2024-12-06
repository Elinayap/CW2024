package com.example.demo.assets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;

public class HeartDisplayTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Deduct hearts by 1
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

    // Add hearts 
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

    // Reset the hearts
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
