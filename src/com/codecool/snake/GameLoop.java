package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.PoliceCar;
import com.codecool.snake.entities.enemies.PoliceDog;
import com.codecool.snake.entities.powerups.Bling;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.Speed;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import static com.codecool.snake.Globals.weed;

public class GameLoop extends AnimationTimer {

    private Pane game;

    GameLoop(Pane pane) {
        super();
        game = pane;
    }
        // This gets called every 1/60 seconds
        @Override

        public void handle(long now){
            int frame = (int) (now / 16666666.6667);
            if (frame % Utils.randomGenerator(300, 800) == 0) new Bling(game);
            if (frame % Utils.randomGenerator(300, 1000) == 0) new SimplePowerup(game);
            if (frame % Utils.randomGenerator(1300, 2500) == 0) new Speed(game);
            if (frame % Utils.randomGenerator(100, 300) == 0) {
                new PoliceCar(game);
                Globals.police.play();
            }
            if (frame % Utils.randomGenerator(100, 300) == 0 ) {
                new PoliceDog(game);
                Globals.barking.play();

            };


            for (GameEntity gameObject : Globals.gameObjects) {
                if (gameObject instanceof Animatable) {
                    Animatable animObject = (Animatable) gameObject;
                    animObject.step();
                }
            }
            Globals.gameObjects.addAll(Globals.newGameObjects);
            Globals.newGameObjects.clear();

            Globals.gameObjects.removeAll(Globals.oldGameObjects);
            Globals.oldGameObjects.clear();
        }
    }

