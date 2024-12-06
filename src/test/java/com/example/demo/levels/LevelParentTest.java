package com.example.demo.levels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.demo.destructible.ActiveActorDestructible;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LevelParentTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Initialize Level
    @Test
    void testLevelInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                LevelParent level = mock(LevelParent.class);

                Scene scene = level.initializeScene();
                assertNotNull(scene);
                assertNotNull(level.getRoot());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    // When enemies moved out screen boundaries
     @Test
    void testEnemyPenetration() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                LevelParent level = mock(LevelParent.class);

                ActiveActorDestructible enemy = mock(ActiveActorDestructible.class);
                level.addEnemyUnit(enemy);
                level.handleEnemyPenetration();
                assertTrue(level.userIsDestroyed());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    // Go to next level
    @Test
    void testNextLevelTransition() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                LevelParent level = mock(LevelParent.class);
                level.goToNextLevel("com.example.demo.levels.LevelTwo");
                assertTrue(level.isChangedState());
            }   finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
        }

    // Collision of projectile with enemies
    //Projectile and enemies should be removed after collisons
    @Test
    void testProjectileCollisionWithEnemy() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {  
                LevelParent level = mock(LevelParent.class);
                ActiveActorDestructible enemy = mock(ActiveActorDestructible.class);
                ActiveActorDestructible projectile = mock(ActiveActorDestructible.class);

                level.addEnemyUnit(enemy);
                level.getRoot().getChildren().add(projectile);
                when(projectile.getBoundsInParent().intersects(enemy.getBoundsInParent())).thenReturn(true);
                level.handleUserProjectileCollisions();

                assertFalse(level.getRoot().getChildren().contains(projectile));
                assertFalse(level.getRoot().getChildren().contains(enemy));
            }  finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    // Remove destroyed actors from screen
    @Test
    void testRemoveDestroyedActors() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                LevelParent level = mock(LevelParent.class);
                ActiveActorDestructible actor = mock(ActiveActorDestructible.class);
                when(actor.isDestroyed()).thenReturn(true);
                level.addEnemyUnit(actor);
                level.removeAllDestroyedActors();
                assertEquals(0, level.getCurrentNumberOfEnemies());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    
}
