package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private int score;
    private boolean gameOn;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        this.pane = pane;
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        score = 0;
        setImage(Globals.snakeHead);
        this.gameOn = true;
        pane.getChildren().add(this);


        addPart(4);
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            System.out.println("Score: " + this.score);
            this.gameOn = false;
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            SnakeBody.bodyElements.add(newPart);
            tail = newPart;
        }
    }

    public void removePart(int numParts) {
        for (int i = 0; i < numParts; i++) {

        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void changeSpeed(int diff) {
        speed += diff;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                speed = 2;
            }
            }, 3000);
        };

    public void addScore(int diff) {
        this.score +=50;
    }

    public boolean isGameOn() {
        return this.gameOn;

    }

    }

