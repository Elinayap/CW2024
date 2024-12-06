package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.demo.UI.MainMenu;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MainTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Launch game
    @Test
    void testMainLaunch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Main mainStart = new Main();
                Stage stage = new Stage();

                assertDoesNotThrow(() -> {
                    mainStart.start(stage);
                });
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    // Display main menu
    @Test
    void testMainMenuDisplay() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                Controller mockController = new Controller(stage);
                MainMenu mainMenu = new MainMenu(stage, mockController);

                assertDoesNotThrow(() -> mainMenu.show());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }
}
