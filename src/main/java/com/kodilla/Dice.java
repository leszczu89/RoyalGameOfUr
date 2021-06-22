package com.kodilla;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Dice {
    private static Button dice;
    private static int number = 1;

    public static Button getDice() {
        return dice;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Dice.number = number;
    }

    public static Button diceCreator(GridPane grid) {

        dice = new Button();
        dice.getStyleClass().add("dice");
        dice.getStylesheets().add("file:src/main/java/com/kodilla/StylesheetFirst.css");
        grid.setHalignment(dice, HPos.CENTER);
        dice.setText(String.valueOf(getNumber()));

        return dice;
    }
}
