package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;


public class BackGround extends GameEntity {

    public BackGround(Pane pane) {
        super(pane);
        setImage(Globals.backGround);
        pane.getChildren().add(this);

    }
}
