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

public class MainMenuTest {

     @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    //Display of main menu
    @Test
    void testMainMenuDisplay() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                MainMenu mainMenu = new MainMenu(stage, mock(Controller.class));

                assertDoesNotThrow(() -> mainMenu.show());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS); 
    }

    //Start Game Button
    @Test
    void testStartGameButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                Controller mockController = mock(Controller.class);
                MainMenu mainMenu = new MainMenu(stage, mockController);

                mainMenu.show();
                assertDoesNotThrow(() -> {
                    mainMenu.startGame();
                });
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS); 
    }

    //Instructions Button
     @Test
    void testInstructionsButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                MainMenu mainMenu = new MainMenu(stage, mock(Controller.class));

                mainMenu.show();
                assertDoesNotThrow(() -> {
                    mainMenu.showInstructions();
                });
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS); 
    }

    //Quit Game Button
    @Test
    void testQuitGameButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage mockStage = mock(Stage.class);
                MainMenu mainMenu = new MainMenu(mockStage, mock(Controller.class));

                mainMenu.show();
                assertDoesNotThrow(() -> mockStage.close()
                );
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS); 
    }

}
