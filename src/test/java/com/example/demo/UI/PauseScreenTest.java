package com.example.demo.UI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;

public class PauseScreenTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Display pause menu
    @Test
    void testPauseMenuDisplay() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);

    Platform.runLater(() -> {
        try {
            Stage stage = new Stage();
            Controller controller = mock(Controller.class);
            PauseScreen pauseScreen = new PauseScreen(stage, controller, null, null);

            assertDoesNotThrow(() -> pauseScreen.show());
        } finally {
            latch.countDown();
        }
    });

        latch.await(1, TimeUnit.SECONDS); 
    }

    // Resume Button
    @Test
    void testResumeButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                Controller mockController = mock(Controller.class);
                Runnable mockResumeAction = mock(Runnable.class);
                PauseScreen pauseScreen = new PauseScreen(stage, mockController, mockResumeAction, null);

                pauseScreen.show();
                assertDoesNotThrow(() -> {
                    mockResumeAction.run();
                });
            } finally {
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.SECONDS);
    }

     // Settings Button
     @Test
     void testSettingsButton() throws InterruptedException {
         CountDownLatch latch = new CountDownLatch(1);
 
         Platform.runLater(() -> {
             try {
                 Stage stage = new Stage();
                 Controller mockController = mock(Controller.class);
                 Runnable mockSettingsAction = mock(Runnable.class);
                 PauseScreen pauseScreen = new PauseScreen(stage, mockController, null, mockSettingsAction);
 
                 pauseScreen.show();
                 assertDoesNotThrow(() -> {
                     pauseScreen.showSettings();
                 });
             } finally {
                 latch.countDown();
             }
         });
 
         latch.await(1, TimeUnit.SECONDS);
     }

    // Exit Button
    @Test
    void testExitButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                Controller mockController = mock(Controller.class);
                PauseScreen pauseScreen = new PauseScreen(stage, mockController, null, null);

                pauseScreen.show();
                assertDoesNotThrow(() -> stage.close());
            } finally {
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.SECONDS);
    }


}
