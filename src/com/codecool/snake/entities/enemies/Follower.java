package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Follower extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 30;

    public Follower(Pane pane) {
        super(pane);

        setImage(Globals.angryEmoji);
        pane.getChildren().add(this);
        double speed = 1d;
        Random rnd = new Random();
        // Starting position
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        double speed = 2d;
        double snakeDirection = SnakeHead.getSnakeHeadPosition();
        setRotate(snakeDirection);

        double newDir =
                Math.toDegrees(Math.atan2(
                        Globals.snakeHeadObj.getY() - this.getY(),
                        Globals.snakeHeadObj.getX() - this.getX()
                        ));

        heading = Utils.directionToVector(newDir, speed);

        Point2D point = new Point2D(Globals.snakeHeadObj.getX() - this.getX(), Globals.snakeHeadObj.getY() - this.getY()).normalize().multiply(speed);


        setX(getX() + point.getX());
        setY(getY() + point.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "30 damage";
    }
}
