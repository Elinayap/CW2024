package com.example.demo.GameState;

public class GameState {

    private int level1Hearts; // Player health for Level 1
    private int level2Hearts; 
    private int shopItem1PurchaseCount; 
    private boolean shopLocked; 
    private static GameState instance = null; 

    private GameState() {
        resetAll();
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    // Getter for level2Hearts
    public int getLevel2Hearts() {
        return level2Hearts;
    }

    // Add hearts to LevelTwo
    public void addLevel2Hearts(int extraHearts) {
        this.level2Hearts += extraHearts;
        System.out.println("Level 2 hearts updated to: " + level2Hearts);
    }

    public int getLevel1Hearts() {
        return level1Hearts;
    }

    // Setter for level1Hearts
    public void setLevel1Hearts(int hearts) {
        this.level1Hearts = hearts;
        System.out.println("Level 1 hearts updated to: " + hearts);
    }
    
    // Reset hearts to default
    public void resetLevel1Hearts() {
        this.level1Hearts = 5;
        System.out.println("Level 1 hearts reset to default: " + level1Hearts);
    }

    public void resetLevel2Hearts() {
        this.level2Hearts = 5;
        System.out.println("Level 2 hearts reset to default: " + level2Hearts);
    }

    public void setLevel2Hearts(int hearts) {
        level2Hearts = hearts;
        System.out.println("Level 2 hearts updated to: " + hearts);
    }

    // Getter for shop purchase count
    public int getShopItem1PurchaseCount() {
        return shopItem1PurchaseCount;
    }

    // Increment shop purchase count
    public void incrementShopItem1PurchaseCount() {
        if (shopItem1PurchaseCount < 2) {
            shopItem1PurchaseCount++;
            System.out.println("Shop Item 1 purchased. Total purchases: " + shopItem1PurchaseCount);
        } else {
            System.out.println("Shop Item 1 purchase limit reached.");
        }
    }

    public boolean isShopLocked() {
        return shopLocked;
    }

    // Reset shop purchase count
    public void resetShop() {
        this.shopItem1PurchaseCount = 0; 
        this.shopLocked = false; // Unlock the shop
        System.out.println("Shop reset to initial values.");
    }

    public void setShopLocked(boolean locked) {
        this.shopLocked = locked;
        System.out.println("Shop locked status set to: " + locked);
    }

    // Reset all game states
    public void resetAll() {
        this.level1Hearts = 5;  // Default hearts for Level 1
        this.level2Hearts = 5;  // Default hearts for Level 2
        this.shopItem1PurchaseCount = 0; // Reset shop purchases
        this.shopLocked = false; // Unlock shop
        System.out.println("GameState reset to initial values.");
    }
}
