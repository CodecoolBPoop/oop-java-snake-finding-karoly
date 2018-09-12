package com.codecool.snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        Utils.newprimaryStage = primaryStage;
        Utils.includeRestartBtn(game);

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT, Color.BURLYWOOD));
        primaryStage.show();
        game.start();

    }

    public void restart(Stage primaryStage){
        primaryStage.close();
        Globals.gameLoop.stop();
        start(primaryStage);
    }

}

