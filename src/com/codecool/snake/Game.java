package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import com.codecool.snake.entities.powerups.Bling;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.Speed;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class Game extends Pane {

    public Game() {



            new SnakeHead(this, 500, 500);

            new SimplePowerup(this);
            new SimplePowerup(this);
            new SimplePowerup(this);
            new SimplePowerup(this);
            new Speed(this);
            new Bling(this);
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
}
