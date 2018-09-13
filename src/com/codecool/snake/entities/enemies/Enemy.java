package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;
import java.util.ListIterator;

public abstract class Enemy extends GameEntity {

    private static final int DISTANCE_FROM_HEAD = 20;
    private static final int DISTANCE_FROM_BODY = 5;
    private static final int ENEMY_DAMAGE_PER_BODY = -2;

    protected Enemy(Pane pane) {
        super(pane);
    }

    protected Enemy() {
    }

    public boolean isSpawningOnSnake(double x, double y) {
        ListIterator<SnakeBody> li = SnakeBody.bodyElements.listIterator();
        //the first body element is more protected from enemy spawning to protect head as well
        if (li.hasNext()){
            SnakeBody firstBody = li.next();
            if(firstBody.getX() <= x+DISTANCE_FROM_HEAD && firstBody.getX() >= x-DISTANCE_FROM_HEAD &&
                    firstBody.getY() <= y+DISTANCE_FROM_HEAD && firstBody.getY() >= y-DISTANCE_FROM_HEAD) {
                return false;
            }
        }
        while (li.hasNext()) {
            SnakeBody currentBody = li.next();
            if ((currentBody.getX() <= (x + DISTANCE_FROM_BODY)) && (currentBody.getX() >= (x - DISTANCE_FROM_BODY)) &&
                (currentBody.getY() <= (y + DISTANCE_FROM_BODY)) && (currentBody.getY() >= (y - DISTANCE_FROM_BODY))) return true;
        }
        return false;
    }

    public void handleEnemySnakeIntersection() {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SnakeBody) {
                    SnakeHead sH = null;
                    ListIterator<GameEntity> gameEntityListIterator = Globals.getGameObjects().listIterator();
                    while (gameEntityListIterator.hasNext()) {
                        GameEntity currentEntity = gameEntityListIterator.next();
                        if (currentEntity instanceof SnakeHead) {
                            sH = ((SnakeHead) currentEntity);
                        }
                    }
                    if(!SnakeBody.bodyElements.isEmpty()){
                        ListIterator<SnakeBody> li = SnakeBody.bodyElements.listIterator();
                        while (li.hasNext()) {
                            SnakeBody currentBody = li.next();
                            if (currentBody.equals(entity)) {
                                sH.setTail(currentBody);
                                while (li.hasNext())
                                    li.next().destroy();
                                sH.changeHealth(ENEMY_DAMAGE_PER_BODY);
                            }
                        }
                    } else {
                        sH.setTail(sH);
                        System.out.println("böe");
                    }

                    System.out.println("Enemy damage. Health is: " + sH.getHealth());
                }
            }
        }
    }
}
