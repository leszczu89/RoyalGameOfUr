package com.kodilla;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;



public class OpponentsPawnReturner {



    public static Pawn returnPawn(int i, ArrayList<Pawn> opponentsPawnsList, ArrayList<Rectangle> fieldsList, int fieldIndex) {

        Pawn opponentsPawn = new Pawn();

        for (Pawn pawn: opponentsPawnsList) {
            if (GridPane.getRowIndex(pawn)==GridPane.getRowIndex(fieldsList.get(fieldIndex + i))&&
                    GridPane.getColumnIndex(pawn)==GridPane.getColumnIndex(fieldsList.get(fieldIndex + i))){
                opponentsPawn = pawn;
            }
        }
        return opponentsPawn;
    }
}
