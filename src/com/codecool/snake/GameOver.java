package com.codecool.snake;

import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class GameOver {

    public static int highScore ;

    public void gameOver(Game game) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game over");
        window.setMinWidth(400);

        Button buttonClose = new Button("Close");
        buttonClose.setPrefSize(100,20);
        buttonClose.setOnAction(event ->
        {
            window.close();
            game.restart();
        });

        Label label = new Label();
        String StringHS = Integer.toString(highScore);
        label.setText("Your score is: "+ StringHS);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,buttonClose);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }

}
