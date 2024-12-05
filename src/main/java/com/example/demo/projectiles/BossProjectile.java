package com.example.demo.projectiles;

/**
 * Represents a projectile fired by the boss.
 * The projectile moves horizontally across the screen and interacts with the game environment.
 */
public class BossProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	 /**
     * Constructs a BossProjectile with the initial y-coordinate.
     *
     * @param initialYPos The initial y-coordinate position of the projectile.
     */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
     * Updates the position of the projectile to move horizontally.
     * Determined by the horizontal velocity.
     */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
	/**
     * Updates the state of the projectile.
     * And also update the position.
     */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
