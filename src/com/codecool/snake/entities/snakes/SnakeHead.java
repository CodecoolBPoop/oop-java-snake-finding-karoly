package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

// displayHealth
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;


public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private static int health = 100;
    private static int score = 0;
    private boolean gameOn;

    private static Text displayHealth = new Text();
    private static Text displayScore = new Text();

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        this.pane = pane;
        setX(xc);
        setY(yc);
        tail = this;
        setImage(Globals.snakeHead);
        this.gameOn = true;
        pane.getChildren().add(this);


        addPart(4);
    }


    // Snake movement
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
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            System.out.println("Score: " + this.score);
            this.gameOn = false;
            Globals.gameLoop.stop();
            Utils.popUpShowWhenGameOver();

        }
    }


    // Change SnakeBody
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


    // Getters
    public static int getScore() {
        return score;
    }

    public static int getHealth(){
        return health;
    }


    // Change fields
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
        score += diff;
        displayScore.setText("Score: " + getScore());
    }

    public void changeHealth(int diff) {
        health += diff;
        displayHealth.setText("Health: " + getHealth());
        System.out.println("Health changed");
    }

    public boolean isGameOn() {
        return this.gameOn;

    }

    public void setTail(SnakeBody snakeBody){
        this.tail = snakeBody;
    }


    // Resetters
    public static void resetScore(){
        score = 0;
    }

    public static void resetHealth(){
        health = 100;
    }

    public static void resetSpeed(){
        speed = 2;
    }


    //  Display Items
    public static void displayHealth(Game game){
        resetHealth();
        displayItem(game,850,50, getHealth() ,displayHealth, "Health: ");
        System.out.println("Health is shown");
    }

    public static void displayScore(Game game){
        displayItem(game,850,80, getScore() ,displayScore, "Score: ");
        System.out.println("Score is shown");
    }

    private static void displayItem(Game game, int setX, int setY, int getter, Text text, String itemName){
        text.setText(itemName + getter);
        text.setX(setX);
        text.setY(setY);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setFill(Color.BROWN);

        game.getChildren().add(text);
    }
}

