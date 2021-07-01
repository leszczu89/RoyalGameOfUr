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

    public static Button getBlackPawnToReturn(int keyValue, Map<Integer, Button> blackPawnOnBoardMap){
        Button blackPawn = null;
        if (!blackPawnOnBoardMap.isEmpty()) {
            for (Map.Entry<Integer, Button> entry : blackPawnOnBoardMap.entrySet()) {
                if (entry.getKey().equals(keyValue)){
                    blackPawn = entry.getValue();
                }
            }
        }
        return blackPawn;
    }
    public static Button getWhitePawnToReturn(int keValue, Map<Integer, Button> whitePawnOnBoardMap){
        Button whitePawn = null;
        if (!whitePawnOnBoardMap.isEmpty()) {
            for (Map.Entry<Integer, Button> entry: whitePawnOnBoardMap.entrySet()){
                if (entry.getKey().equals(keValue)){
                    whitePawn = entry.getValue();
                }
            }
        }
        return whitePawn;
    }
}
