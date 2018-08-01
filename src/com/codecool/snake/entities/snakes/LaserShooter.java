package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class LaserShooter extends GameEntity implements Animatable {

    private static final float speed = 6;

    public LaserShooter(Pane pane) {
        super(pane);
        setX(Globals.snakeHeadObj.getX());
        setY(Globals.snakeHeadObj.getY());
        setImage(Globals.laser);
        pane.getChildren().add(this);

    }

    public void step() {

        if (isOutOfBounds()) {
            destroy();
        }

        setRotate(getSnakePosition());
        Point2D heading = Utils.directionToVector(getRotate(), speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

    }

    public double getSnakePosition() {
        double snakePosition = SnakeHead.getSnakeHeadPosition();
        return snakePosition;
    }

}
