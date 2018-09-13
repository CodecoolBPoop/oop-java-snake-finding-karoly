package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class PoliceDog extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private boolean firstBodyFound;

    public PoliceDog(Pane pane) {
        super(pane);

        setImage(Globals.policeDog);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
        while (true) {
            double xc = (rnd.nextDouble() * Globals.WINDOW_WIDTH);
            double yc = (rnd.nextDouble() * Globals.WINDOW_HEIGHT);
            if (isSpawningOnSnake(xc, yc)) {
                System.out.println("cant spawn here");
                continue;
            } else {
                setX(xc);
                setY(yc);
                break;
            }
        }
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    public PoliceDog( Pane pane, double x, double y) {
        super(pane);
        setImage(Globals.policeDog);
        pane.getChildren().add(this);

        setX(x);
        setY(y);

        int speed = 1;
        Random rnd = new Random();

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }


    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SnakeBody) {
                    SnakeHead sH = null;
                    firstBodyFound = false;
                    ListIterator<GameEntity> gameEntityListIterator = Globals.getGameObjects().listIterator();
                    while (gameEntityListIterator.hasNext()) {
                        GameEntity currentEntity = gameEntityListIterator.next();
                        if (currentEntity instanceof SnakeHead) {
                            sH = ((SnakeHead) currentEntity);
                        }
                    }
                    ListIterator<SnakeBody> li = SnakeBody.bodyElements.listIterator();
                    while (li.hasNext()) {
                        SnakeBody currentBody = li.next();
                        if (currentBody.equals(entity)) {
                            sH.setTail(currentBody);
                            while (li.hasNext())
                                li.next().destroy();
                        }
                    }
                    System.out.println("Dog intersects snakebody.");
                }
            }
        }

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
