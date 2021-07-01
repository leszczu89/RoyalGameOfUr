package com.kodilla;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;


public class PawnsChecker {

    public static boolean checkIfPawnIsOnField(List<Pawn> pawnsList, Node field) {
        boolean result = false;
        for (Node btn: pawnsList){
            result= GridPane.getRowIndex(field).equals(GridPane.getRowIndex(btn)) && GridPane.getColumnIndex(field).equals(GridPane.getColumnIndex(btn));

        }
        return result;
    }
    public static boolean checkIfBlackPawnIsOnField(Map<Integer, Button> pawnsList, int fieldIndex) {

        return pawnsList.containsKey(fieldIndex);
    }
    public static boolean checkIfWhitePawnIsOnField(Map<Integer, Button> pawnsList, int fieldIndex) {

        return pawnsList.containsKey(fieldIndex);
    }
}
