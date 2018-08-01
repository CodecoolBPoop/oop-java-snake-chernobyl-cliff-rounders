package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class LaserShooter extends GameEntity implements Animatable {

    private final static float speed = 6;
    private int xc;
    private int yc;

    public LaserShooter(Pane pane, int xc, int yc) {
        super(pane);
        setY(yc);
        setImage(Globals.laser);
        pane.getChildren().add(this);

    }

    public void step() {
        double dir = getRotate();
        setRotate(dir);

        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    System.out.println(interactable.getMessage());
                }
            }
        }


    }

}
