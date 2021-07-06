package com.kodilla;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class FieldWithPawnIndexReturner {

    public int returnIndexOfFieldWithPawn(List<Rectangle> fieldsList, Pawn pawn){
        Rectangle fieldWithPawn = new Rectangle();

        for (Rectangle field : fieldsList) {
            if (GridPane.getColumnIndex(pawn).equals(GridPane.getColumnIndex(field)) && GridPane.getRowIndex(pawn).equals(GridPane.getRowIndex(field))) {
                fieldWithPawn = field;
                break;
            }
        }
        return fieldsList.indexOf(fieldWithPawn);
    }
}
