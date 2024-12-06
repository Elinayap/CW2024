package com.example.demo.UI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.GameState.GameState;
import com.example.demo.levels.LevelParent;

import javafx.application.Platform;
import javafx.stage.Stage;
import static org.mockito.Mockito.mock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ShopTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Display the shop
    @Test
    void testShopDisplay() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelParent currentLevel = mock(LevelParent.class);
                Shop shop = new Shop(stage, currentLevel);

                assertDoesNotThrow(() -> shop.show());
            } finally {
                latch.countDown(); 
            }
        });

        latch.await(1, TimeUnit.SECONDS); 
    }

    // Shop is locked
    @Test
    void testShopLocked() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelParent currentLevel = mock(LevelParent.class);
                Shop shop = new Shop(stage, currentLevel);

                GameState.getInstance().setShopLocked(true);
                assertDoesNotThrow(() -> shop.show());
            } finally {
                latch.countDown(); 
            }
        });

        latch.await(1, TimeUnit.SECONDS); 
    }

    // Shop's popup message
    @Test
    void testShowShopPopup() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                assertDoesNotThrow(() -> Shop.showShopPopup("", ""));
            } finally {
                latch.countDown(); 
            }
        });

        latch.await(1, TimeUnit.SECONDS); 
    }
}
