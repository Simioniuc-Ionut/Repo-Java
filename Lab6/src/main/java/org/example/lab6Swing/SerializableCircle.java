package org.example.lab6Swing;

import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.Serializable;

public class SerializableCircle implements Serializable {
    private final double centerX;
    private final double centerY;
    private final double radius;

    public SerializableCircle(Circle circle) {
        this.centerX = circle.getCenterX();
        this.centerY = circle.getCenterY();
        this.radius = circle.getRadius();
    }

    // Metode getter pentru a accesa datele când este necesar
    public double getCenterX() { return centerX; }
    public double getCenterY() { return centerY; }
    public double getRadius() { return radius; }
}
