package com.codecool.snake;


import com.codecool.snake.entities.enemies.Batman;
import com.codecool.snake.entities.enemies.Follower;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.DifferentPowerup1;
import com.codecool.snake.entities.powerups.DifferentPowerup2;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Game extends Pane {

    public Game() {

        init();
    }

    private void init() {
        Globals.snakeHeadObj = new SnakeHead(this, 500, 500);
        this.getChildren().add(this.addVBox());

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new Batman(this);
        new Batman(this);
        new Batman(this);
        new Batman(this);
        new Follower(this);
        new Follower(this);
        new Follower(this);
        new Follower(this);


        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new DifferentPowerup1(this);
        new DifferentPowerup1(this);
        new DifferentPowerup1(this);

        new DifferentPowerup2(this);
        new DifferentPowerup2(this);
        new DifferentPowerup2(this);
        new DifferentPowerup2(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void restart() {

        Globals.gameObjects.removeAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();
        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
        Globals.gameObjects.clear();
        GameOver.highScore =0;
        SnakeHead.setSpeed(2);
        Globals.leftKeyDown= false;
        Globals.rightKeyDown= false;


        getChildren().clear();
        init();
        start();

    }

    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setLayoutX(10);
        vbox.setLayoutY(20);

        Button restartb = new Button("Restart");
        restartb.setPrefSize(100,20);
        restartb.setOnAction(event->
        {
            Globals.gameLoop.stop();
            restart();
        });
        vbox.getChildren().addAll(restartb);
        return vbox;
    }


}
