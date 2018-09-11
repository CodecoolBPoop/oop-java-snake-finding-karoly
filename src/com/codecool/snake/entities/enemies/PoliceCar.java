package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class PoliceCar extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;


    public PoliceCar(Pane pane) {
        super(pane);

        setImage(Globals.policeCar);
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

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
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
        return "car damage";
    }
}
