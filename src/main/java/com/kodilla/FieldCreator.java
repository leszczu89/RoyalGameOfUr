package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FieldCreator {

    public static Rectangle createField(){
        Rectangle rectangle = new Rectangle(100, 100, Color.GOLDENROD);
        rectangle.setStyle("-fx-background-color: #E1B151,#A8740A; -fx-border-radius: 5");

        return rectangle;
    }
}
