package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Random;

public class Bling extends GameEntity implements Interactable{

    public Bling (Pane pane) {
        super(pane);
        setImage(Globals.bling);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

    }


    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addScore(50);
        destroy();
        Globals.coins.play();

    }

    @Override
    public String getMessage() { return "Blingbling"; }
}
