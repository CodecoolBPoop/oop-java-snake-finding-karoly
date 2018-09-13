package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

//for button
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


// for pop up
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;


public class Utils {

    public static Game currentGame;

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), -length * Math.cos(directionInRadians));
        return heading;

    }

    public static int randomGenerator(int low, int high) {
        Random random = new Random();
        int result = random.nextInt(high-low) + low;
        return result;

    }

    // display a restart button and restart the application
    public static void includeRestartBtn(Game game) {
        currentGame = game;
        Button button1 = new Button("Restart");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("The application restarts");
                currentGame.restart();

            }
        });
        game.getChildren().add(button1);
    }


    // display popup and end game
    public static void popUpShowWhenGameOver() {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.NONE, "Gameover! Would you like to restart the game? ", ButtonType.YES, ButtonType.NO);

            System.out.println("Pop up alert");

            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                System.out.println("Restarting");
                currentGame.restart();

            } else if (alert.getResult() == ButtonType.NO) {
                System.out.println("Exit game");
                Platform.exit();
            }
        });
    }

}



