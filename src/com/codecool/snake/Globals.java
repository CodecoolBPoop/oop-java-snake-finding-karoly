package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import com.codecool.snake.Game;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;

import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image weed = new Image("weed.png");
    public static Image speedPill = new Image("speed_pill.png");
    public static Image bling = new Image("bling.png");
    public static Image policeCar = new Image("police_car.png");
    public static Image policeDog = new Image("police_dog.png");
    public static Image hos = new Image("hos.jpg");
    public static Background backgr = new Background(new BackgroundFill(new ImagePattern(hos), CornerRadii.EMPTY, Insets.EMPTY ));
    public static AudioClip barking = new AudioClip(new File("resources/dog_bark4.wav").toURI().toString());
    public static AudioClip police = new AudioClip(new File("resources/police_siren.wav").toURI().toString());
    public static AudioClip ganja = new AudioClip(new File("resources/ganja.wav").toURI().toString());
    public static AudioClip coins = new AudioClip(new File("resources/coins.mp3").toURI().toString());
    //.. put here the other images you want to use



    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
