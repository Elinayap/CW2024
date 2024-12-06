package com.example.demo.projectiles;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EnemyProjectileTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Test if the position of the projectile updates correctly based on its velocity
    @Test
    void testEnemyProjectileUpdatePos() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                double initialXPos = 100;
                double initialYPos = 200;
                EnemyProjectile enemyProjectile = new EnemyProjectile(initialXPos, initialYPos);

                enemyProjectile.updateActor();
                assertEquals(initialXPos + EnemyProjectile.HORIZONTAL_VELOCITY,enemyProjectile.getX());
                assertEquals(initialYPos,enemyProjectile.getY());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    // Initialize EnemyProjectile
    @Test
    void testEnemyProjectileInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                double initialXPos = 100;
                double initialYPos = 200;
                EnemyProjectile enemyProjectile = new EnemyProjectile(initialXPos, initialYPos);

                assertEquals(initialXPos, enemyProjectile.getX());
                assertEquals(initialYPos, enemyProjectile.getY());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }


}
