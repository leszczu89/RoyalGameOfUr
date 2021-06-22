package com.kodilla;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class PawnsChecker {

    public static boolean check(ArrayList<Node> listButton, GridPane gridPane, Node rectangle) {
        boolean result = false;
        for (Node btn: listButton){
            result= GridPane.getRowIndex(rectangle).equals(GridPane.getRowIndex(btn)) && GridPane.getColumnIndex(rectangle).equals(GridPane.getColumnIndex(btn));
            break;
        }
        return !result;
    }
}
