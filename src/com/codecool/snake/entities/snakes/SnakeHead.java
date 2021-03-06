package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.GameOver;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private static double snakeHeadPosition;
    public static int health;
    private int speedTimer;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        this.speedTimer = 0;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    public static double getSnakeHeadPosition() {
        return snakeHeadPosition;
    }

    public void step() {
        Point2D heading = getSnakeHeading();

        createShotIfNeeded();

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a power up
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
            new GameOver().gameOver((Game) this.pane);
        }
    }

    public void createShotIfNeeded() {
        if (Globals.shiftKeyDown) {
            new LaserShooter(pane);
        }
    }

    public Point2D getSnakeHeading() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
            this.snakeHeadPosition = getRotate();

        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
            this.snakeHeadPosition = getRotate();
        }

        // set rotation and position
        setRotate(dir);
        return Utils.directionToVector(dir, speed);
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;

        }
            GameOver.highScore+=numParts*10;
    }


    public void changeHealth(int diff) {
        health += diff;
    }

    public static void setSpeed(float speed) {
        SnakeHead.speed = speed;
    }

    public int getSpeedTimer() {
        return speedTimer;
    }

    public void setSpeedTimer(int speedTimer) {
        this.speedTimer = speedTimer;
    }

    public void setSpeedTimer() {
        this.speedTimer -= 1;
    }

    public static String getHealth() {
        return Integer.toString(health);



    }
}
