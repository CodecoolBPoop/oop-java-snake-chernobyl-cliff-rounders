package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.InteractableLaser;
import com.codecool.snake.entities.snakes.LaserShooter;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable, InteractableLaser {

    private Point2D heading;
    private static final int damage = 10;
    public Pane myGame;

    public SimpleEnemy(Pane pane) {
        super(pane);
        myGame = pane;

        setImage(Globals.drunkMan);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
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
            Game.addSimpleEnemy(myGame);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public void apply(LaserShooter laserShooter) {
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }


    public Pane getMyGame() {
        return myGame;
    }
}
