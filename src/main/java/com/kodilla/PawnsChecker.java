package com.kodilla;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;


public class PawnsChecker {

    public static boolean checkIfPawnIsOnField(List<Pawn> pawnsList, Node field) {
        boolean result = false;
        for (Pawn pawn: pawnsList){
            if(GridPane.getRowIndex(pawn).equals(GridPane.getRowIndex(field))&&GridPane.getColumnIndex(pawn).equals(GridPane.getColumnIndex(field))){
                result = true;
                break;
            } else result = false;
        }
        return result;
    }
}
