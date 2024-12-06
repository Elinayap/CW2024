package com.example.demo.GameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GameStateTest {

    //Test all default values
    @Test
    void testDefaultGameStateValues() {
        GameState gameState = GameState.getInstance();
        assertEquals(5, gameState.getLevel1Hearts());
        assertEquals(5, gameState.getLevel2Hearts());
        assertEquals(0, gameState.getShopItem1PurchaseCount());
        assertFalse(gameState.isShopLocked());
    }

    // Shop purchases count
    @Test
    void testShopItemPurchaseCount() {
        GameState gameState = GameState.getInstance();

        gameState.incrementShopItem1PurchaseCount();
        assertEquals(1, gameState.getShopItem1PurchaseCount());
        gameState.incrementShopItem1PurchaseCount();
        assertEquals(2, gameState.getShopItem1PurchaseCount());
        //Exceed the limit
        gameState.incrementShopItem1PurchaseCount();
        assertEquals(2, gameState.getShopItem1PurchaseCount());

        gameState.resetShop();
        assertEquals(0, gameState.getShopItem1PurchaseCount());
    }
    
    // Shop status
    @Test
    void testShopLockedStatus() {
        GameState gameState = GameState.getInstance();
        // Test shop not lock or lock
        gameState.setShopLocked(true);
        assertTrue(gameState.isShopLocked());
        gameState.setShopLocked(false);
        assertFalse(gameState.isShopLocked());
    }

    @Test
    void testLevel2HeartsExtraHearts() {
        GameState gameState = GameState.getInstance();

        gameState.setLevel2Hearts(3);
        assertEquals(3, gameState.getLevel2Hearts());
        // Add extra hearts
        gameState.addLevel2Hearts(2);
        assertEquals(5, gameState.getLevel2Hearts());
        // Reset hearts to default
        gameState.resetLevel2Hearts();
        assertEquals(5, gameState.getLevel2Hearts());
    }
}
