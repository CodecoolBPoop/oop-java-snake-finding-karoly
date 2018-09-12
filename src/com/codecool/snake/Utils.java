package com.codecool.snake;

import javafx.geometry.Point2D;

import java.util.Random;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static int randomGenerator(int low, int high) {
        Random random = new Random();
        int result = random.nextInt(high-low) + low;
        return result;


    }
}
