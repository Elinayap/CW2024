package com.example.demo;

public class UserPlane extends FighterPlane {

    private static final String IMAGE_NAME = "userplane.png";
    private static final double Y_UPPER_BOUND = -40;
    private static final double Y_LOWER_BOUND = 600.0;
    private static final double X_LEFT_BOUND = 0.0;
    private static final double X_RIGHT_BOUND = 600.0;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 150;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HORIZONTAL_VELOCITY = 8;
    private static final int PROJECTILE_X_POSITION = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private int verticalVelocityMultiplier;
    private int horizontalVelocityMultiplier;
    private int numberOfKills;

    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;
    }

    @Override
    public void updatePosition() {
        //Control vertical movement
        if (isMovingVertical()) {
            double initialTranslateY = getTranslateY();
            this.moveVertical(VERTICAL_VELOCITY * verticalVelocityMultiplier);
            double newPositionY = getLayoutY() + getTranslateY();
            if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY); 
            }
        }

        //Control horizontal movement
        if (isMovingHorizontal()) {
            double initialTranslateX = getTranslateX();
            this.moveHorizontal(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
            double newPositionX = getLayoutX() + getTranslateX();
            if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
                this.setTranslateX(initialTranslateX); 
            }
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
    }

    private boolean isMovingVertical() {
        return verticalVelocityMultiplier != 0;
    }

    private boolean isMovingHorizontal() {
        return horizontalVelocityMultiplier != 0;
    }

    public void moveUp() {
        verticalVelocityMultiplier = -1;
    }

    public void moveDown() {
        verticalVelocityMultiplier = 1;
    }

    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    public void stopVerticalMove() {
        verticalVelocityMultiplier = 0;
    }

    public void stopHorizontalMove() {
        horizontalVelocityMultiplier = 0;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void incrementKillCount() {
        numberOfKills++;
    }

	//Move the plane vertically by specified amount
	//Move distance along Y-axis, positive values down and negative values up
    private void moveVertical(double Yaxis) {
        this.setTranslateY(this.getTranslateY() + Yaxis);
    }

	//Move the plane vertically by specified amount
	//Move distance along X-axis, positive values right and negative values left
    private void moveHorizontal(double Xaxis) {
        this.setTranslateX(this.getTranslateX() + Xaxis);
    }
}
