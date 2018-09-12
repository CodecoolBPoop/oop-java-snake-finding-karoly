package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;


import javafx.scene.control.*; //button class
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// for pop up
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import  javafx.scene.control.ChoiceDialog;

import java.util.Optional;
import javafx.application.Platform;


public class Utils {

    public static Stage newprimaryStage;

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
        Button button1 = new Button("Restart");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("The application restarts");
                restart();


            }
        });
        game.getChildren().add(button1);
    }

    public static void restart() {
        Main main = new Main();
        main.restart(newprimaryStage);

    }

    // display popup and end game
    public static void popUpShowWhenGameOver() {
//        Alert alert = new Alert(AlertType.NONE, "Gameover! Would you like to restart the game? ", ButtonType.YES, ButtonType.NO);
//
//        System.out.println("Pop up alert");
//
//        alert.showAndWait();
//
//        if (alert.getResult() == ButtonType.YES) {
//            System.out.println("Answer yes");
//            alert.close();
//            restart();
//
//
//        }else if(alert.getResult() == ButtonType.NO){
//            System.out.println("Exit game");
//            Platform.exit();
//            }
//        }

        //Platform.runLater(()-> {
        //});
    }
}

