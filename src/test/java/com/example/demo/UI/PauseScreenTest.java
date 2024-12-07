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

/**
 * Junit tests for the {@link PauseScreen} class.
 */
public class PauseScreenTest {

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
     * Test if the PauseScreen can be displayed.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
    @Test
    void testPauseScreenDisplay() throws InterruptedException {
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

    /**
     * Test the function of the Resume button.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
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

    /**
     * Test the function of the Settings button.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
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

    /**
     * Test the function of the Exit button.
     *
     * @throws InterruptedException if the JavaFX setup is interrupted.
     */
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
