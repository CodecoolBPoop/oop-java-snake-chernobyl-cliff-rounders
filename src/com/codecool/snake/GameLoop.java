package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.DifferentPowerup1;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof SnakeHead) { ;
                if (((SnakeHead) gameObject). getSpeedTimer() != 0) {
                    ((SnakeHead) gameObject).setSpeedTimer();
                } else if (((SnakeHead) gameObject).getSpeedTimer() == 0) {
                    SnakeHead.setSpeed(2);
                }
                System.out.println(((SnakeHead) gameObject).getSpeedTimer());
            }
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
        Game.label.setText(SnakeHead.getHealth());

    }
}
