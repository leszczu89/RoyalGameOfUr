package com.kodilla;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import java.util.List;
import java.util.Map;


public class OpponentsPawnReturner {

    public static Pawn getOpponentsPawnToReturn(int i, List<Pawn> opponentsPawnsList, List<Rectangle> fieldsList, int fieldIndex) {

        Pawn opponentsPawn = null;

        for (Pawn pawn: opponentsPawnsList) {
            if (GridPane.getRowIndex(pawn).equals(GridPane.getRowIndex(fieldsList.get(fieldIndex + i))) &&
                    GridPane.getColumnIndex(pawn).equals(GridPane.getColumnIndex(fieldsList.get(fieldIndex + i)))){
                opponentsPawn = pawn;
            }
        }
        return opponentsPawn;
    }
}
