package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class PawnsChecker {

    public static boolean check(ArrayList<Pawn> pawnsList, Node field) {
        boolean result = false;
        for (Node btn: pawnsList){
            result= GridPane.getRowIndex(field).equals(GridPane.getRowIndex(btn)) && GridPane.getColumnIndex(field).equals(GridPane.getColumnIndex(btn));

        }
        return result;
    }
}
