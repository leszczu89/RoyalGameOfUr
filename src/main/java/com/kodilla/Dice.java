package com.kodilla;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Dice {

    public Button createDiceButton() {

        Button dice = new Button();
        dice.getStyleClass().add("dice");

        GridPane.setHalignment(dice, HPos.CENTER);
        dice.setText("1");

        return dice;
    }
}
