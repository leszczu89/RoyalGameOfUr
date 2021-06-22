package com.kodilla;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PawnCreator {

    private static Button button;

    public static Button getButton() {
        return button;
    }

    public Button pawnCreator(GridPane grid, String color) {
        button = new Button();

        grid.setHalignment(button, HPos.CENTER);
        button.setStyle(
                "-fx-background-radius: 5em;-fx-max-width: 50px;-fx-max-height: 50px;-fx-background-color:" + color);
        button.getStyleClass().add("file:src/main/java/com/kodilla/StylesheetFirst.css");


        return button;
    }
}
