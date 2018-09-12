package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeBody;
import javafx.scene.layout.Pane;

import java.util.ListIterator;

public abstract class Enemy extends GameEntity {

    protected Enemy(Pane pane) {
        super(pane);
    }

    public boolean isSpawningOnSnake(double x, double y) {
        ListIterator<SnakeBody> li = SnakeBody.bodyElements.listIterator();
        while (li.hasNext()) if (li.next().getX() == x && li.next().getY() == y) return true;
        return false;
    }

}
