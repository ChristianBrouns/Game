package game;

import javafx.scene.canvas.GraphicsContext;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Christian on 1-4-2016.
 */
public class Sprite {

    public double positionX;
    public double positionY;
    private double velocityX;
    private double velocityY;

    public Sprite() {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;

    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;

    }

    public String toString() {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

    public static int setDirection() {
        int direction = (int) (Math.random() * 10) + 1;
        return direction;
    }
}


