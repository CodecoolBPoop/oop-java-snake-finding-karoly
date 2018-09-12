package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeBody;
import javafx.scene.layout.Pane;
import java.util.ListIterator;

public abstract class Enemy extends GameEntity {

    private static final int DISTANCE_FROM_HEAD = 20;
    private static final int DISTANCE_FROM_BODY = 5;

    protected Enemy(Pane pane) {
        super(pane);
    }

    protected Enemy() {
    }

    public boolean isSpawningOnSnake(double x, double y) {
        ListIterator<SnakeBody> li = SnakeBody.bodyElements.listIterator();
        //the first body element is more protected from enemy spawning to protect head as well
        if(li.next().getX() <= x+DISTANCE_FROM_HEAD && li.next().getX() >= x-DISTANCE_FROM_HEAD &&
           li.next().getY() <= y+DISTANCE_FROM_HEAD && li.next().getY() >= y-DISTANCE_FROM_HEAD) {
            return false;
        }
        while (li.hasNext()) if ((li.next().getX() <= (x + DISTANCE_FROM_BODY)) && (li.next().getX() >= (x - DISTANCE_FROM_BODY)) &&
                (li.next().getY() <= (y + DISTANCE_FROM_BODY)) && (li.next().getY() >= (y - DISTANCE_FROM_BODY))) return true;
        return false;
    }

}
