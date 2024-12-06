package com.example.demo.actors;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;

import com.example.demo.assets.ShieldImage;
import com.example.demo.projectiles.BossProjectile;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

public class BossProjectileTest {

    @Test
    void testProjectileVelocity_LevelThree() throws InterruptedException {
        //Use a CountDownLatch to wait for JavaFX initialization 
        //If not the test will finish running before JavaFX finishs processing
        CountDownLatch latch = new CountDownLatch(1);

        //This starts the JavaFX runtime if it isn't already running
        // It is important because tests usually run outside the JavaFX application environment
        Platform.startup(() -> {
            try {
                ShieldImage shieldImage = new ShieldImage(0, 0);
                Boss boss = new Boss(shieldImage, "LevelThree");

                BossProjectile projectile = (BossProjectile) boss.fireProjectile();

                assertNotNull(projectile, "Projectile will not be null.");
                //System.out.println("Projectile velocity: " + projectile.getHorizontalVelocity());
                assertEquals(-50, projectile.getHorizontalVelocity(), "Projectile velocity should be -50 in Level Three.");
            } finally {
                latch.countDown(); // Signal the test can proceed
            }
        });

        // Wait for the JavaFX to complete
        latch.await();
    }
}
