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

public class BossTest {

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    // Initialization of boss 
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

    // Boss take damage with no shield
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

     // Boss take damage with shield
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

    // Speed of projectile in LevelThree
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
