package com.example.demo.actors;

import com.example.demo.destructible.ActiveActorDestructible;
import com.example.demo.projectiles.UserProjectile;

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

    private int verticalVelocityMultiplier;
    private int horizontalVelocityMultiplier;
    private int numberOfKills;

    private UserProjectile activeProjectile; 

    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;
    }

    //Control the position of user plane
    @Override
    public void updatePosition() {
        //Control vertical movement
        if (isMovingVertical()) {
            double initialTranslateY = getTranslateY();
            moveVertical(VERTICAL_VELOCITY * verticalVelocityMultiplier);
            double newPositionY = getLayoutY() + getTranslateY();
            if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
                setTranslateY(initialTranslateY);
            }
        }

        //Control horizontal movement
        if (isMovingHorizontal()) {
            double initialTranslateX = getTranslateX();
            moveHorizontal(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
            double newPositionX = getLayoutX() + getTranslateX();
            if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
                setTranslateX(initialTranslateX);
            }
        }

        //Sync with the user projectile
        if (activeProjectile != null) {
            //Get the position of the user plane
            activeProjectile.syncWithPlane(
                getBoundsInParent().getMinX(),
                getBoundsInParent().getMinY(),
                getBoundsInParent().getWidth(),
                getBoundsInParent().getHeight()
            );
        }
    }

    public void assignProjectile(UserProjectile projectile) {
        this.activeProjectile = projectile;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        //Check if there is active projectile
        if (activeProjectile != null) {
            //Make the projectile to fire so it moves on its own
            activeProjectile.fire(); 
            //Remove it from being tracked so new one can be fired
            activeProjectile = null; 
        }

        
        UserProjectile projectile = new UserProjectile(
            //Start to shoot at the tip of user plane
            getBoundsInParent().getMaxX(), 
            //Set the position at center vertically
            getBoundsInParent().getMinY() + getBoundsInParent().getHeight() / 2 - UserProjectile.IMAGE_HEIGHT / 2 
        );
        //Assign projectile to active
        assignProjectile(projectile); 
        return projectile;
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

    public int getKillCount() {
        return numberOfKills;
    }

    //Move the plane vertically by specified amount
	//Move distance along Y-axis, positive values down and negative values up
    private void moveVertical(double Yaxis) {
        setTranslateY(getTranslateY() + Yaxis);
    }

    //Move the plane vertically by specified amount
	//Move distance along X-axis, positive values right and negative values left
    private void moveHorizontal(double Xaxis) {
        setTranslateX(getTranslateX() + Xaxis);
    }
    

    
}
