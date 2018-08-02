package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.InteractableLaser;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class LaserShooter extends GameEntity implements Animatable {

    private static final float speed = 6;
    private Point2D heading;

    public LaserShooter(Pane pane) {
        super(pane);
        setX(Globals.snakeHeadObj.getX());
        setY(Globals.snakeHeadObj.getY());
        setImage(Globals.laser);
        pane.getChildren().add(this);
        this.heading = Globals.snakeHeadObj.getSnakeHeading();

    }

    public void step() {

        if (isOutOfBounds()) {
            destroy();
        }

        setX(getX() + heading.getX() * speed);
        setY(getY() + heading.getY() * speed);

        checkIfCollided();

    }

    public void checkIfCollided() {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof InteractableLaser) {
                    InteractableLaser interactableLaser = (InteractableLaser) entity;
                    interactableLaser.apply(this);
                    System.out.println(interactableLaser.getMessage());
                }
            }
        }
    }

}
