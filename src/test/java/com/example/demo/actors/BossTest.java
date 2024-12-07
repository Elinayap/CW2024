package com.example.demo.actors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.demo.assets.ShieldImage;
import com.example.demo.projectiles.BossProjectile;
import javafx.application.Platform;


/**
 * Junit tests for the {@link Boss} class.
 */
public class BossTest {

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
     * Tests the initialization of Boss.
     * Ensures the Boss starts with the correct health and it will not be destroyed.
     *
     * @throws InterruptedException if the JavaFX thread is interrupted.
     */
    @Test
    void testBossInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                ShieldImage mockShieldImage = mock(ShieldImage.class);
                Boss boss = new Boss(mockShieldImage, "LevelTwo");

                assertEquals(10, boss.getHealth());
                assertFalse(boss.isDestroyed());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

     /**
     * Tests boss to take damage with no shield
     * Ensures the boss's health decreases when taking damage.
     *
     * @throws InterruptedException if the JavaFX thread is interrupted.
     */
     @Test
    void testBossTakeDamageNoShield() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                ShieldImage mockShieldImage = mock(ShieldImage.class);
                when(mockShieldImage.isVisible()).thenReturn(false); 
                Boss boss = new Boss(mockShieldImage, "LevelTwo");

                boss.takeDamage();
      
                assertEquals(9, boss.getHealth());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests for boss that does not take damage with shield.
     * Ensures the boss's health does not decreases when taking damage.
     *
     * @throws InterruptedException if the JavaFX thread is interrupted.
     */
    @Test
    void testBossTakeDamageWithShield() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                ShieldImage mockShieldImage = mock(ShieldImage.class);
                when(mockShieldImage.isVisible()).thenReturn(true);
                Boss boss = new Boss(mockShieldImage, "LevelTwo");

                boss.takeDamage();
                // Expected health does not decrease
                assertEquals(10, boss.getHealth());  
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the speed of the projectile in LevelThree.
     * Ensures the horizontal velocity matches the expected value.
     *
     * @throws InterruptedException if the JavaFX thread is interrupted.
     */
    @Test
    void testBossSpeedFireProjectileLevelThree() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                ShieldImage mockShieldImage = mock(ShieldImage.class);
                Boss boss = new Boss(mockShieldImage, "LevelThree");
                BossProjectile projectile = (BossProjectile) boss.fireProjectile();

                if (projectile != null) {
                    assertEquals(-24, projectile.getHorizontalVelocity());
                }
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }
}
