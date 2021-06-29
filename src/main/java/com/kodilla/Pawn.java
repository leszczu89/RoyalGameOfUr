package com.kodilla;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Pawn extends Button{

    public Pawn createPawn(String color) {

        Pawn pawn = new Pawn();
        GridPane.setHalignment(pawn, HPos.CENTER);
        pawn.setStyle(
                "-fx-background-radius: 5em;-fx-max-width: 50px;-fx-max-height: 50px;-fx-background-color:" + color);
        pawn.setId(color);
        try {
            pawn.getStyleClass().add(getClass().getResource("/StyleSheets/primaryStageStyle.css").toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return pawn;
    }


}
