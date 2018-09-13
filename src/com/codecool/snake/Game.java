package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import com.codecool.snake.entities.powerups.Bling;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.Speed;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


import javafx.scene.control.*;


public class Game extends Pane {

    public Game() {
           init();
        }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });


        Globals.gameLoop = new GameLoop(this);
        Globals.gameLoop.start();
    }

    public void restart(){
        clear();
        init();
        Globals.gameLoop.start();
        SnakeHead.resetScore();
        SnakeHead.resetSpeed();
    }


    private void clear(){
        getChildren().clear();
        Globals.gameObjects.clear();
    }

    private void init(){
        Utils.includeRestartBtn(this);
        SnakeHead.displayHealth(this);
        SnakeHead.displayScore(this);

        new SnakeHead(this, 500, 500);


        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new Speed(this);
        new Bling(this);


    }
}
