package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.Random;

public class Speed extends GameEntity implements Interactable{

    public Speed (Pane pane) {
        super(pane);
        setImage(Globals.speedPill);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

    }


    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeSpeed(3);
        snakeHead.addScore(10);
        destroy();

    }

    @Override
    public String getMessage() { return "Speed"; }
}
