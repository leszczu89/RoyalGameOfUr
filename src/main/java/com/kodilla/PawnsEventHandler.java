package com.kodilla;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;

import static com.kodilla.TheRoyalGameOfUr.getFirstPlayersCounter;
import static com.kodilla.TheRoyalGameOfUr.setFirstPlayersCounter;

public class PawnsEventHandler {

    public static void handle(ArrayList<Node> pawnsList, Button dice, GridPane grid, ArrayList<Rectangle> fieldsList) {
        ArrayList<Node> listPawn = pawnsList;
        ArrayList<Rectangle> listFields = fieldsList;
        Rectangle fieldWithPawn = new Rectangle();
        for (Node pawn : listPawn) {

            int column = grid.getColumnIndex(pawn);
            int row = grid.getRowIndex(pawn);


            for(Rectangle field : listFields){
                if (column==grid.getColumnIndex(field)&&row==grid.getRowIndex(field)){
                    fieldWithPawn = field;
                }
            }
            int fieldIndex = listFields.indexOf(fieldWithPawn);
            Button button;
            button = (Button) pawn;
            button.setOnAction(event -> {
                switch (String.valueOf(dice.getText())) {
                    case "1":
                        if (fieldIndex + 1 == listFields.size()) {
                            grid.getChildren().remove(pawn);
                            grid.add(pawn, 6, 1);
                            setFirstPlayersCounter(getFirstPlayersCounter() + 1);
                        } else if (PawnsChecker.check(listPawn, grid, listFields.get(fieldIndex + 1))) {
                            grid.getChildren().remove(pawn);
                            grid.add(pawn, grid.getColumnIndex(listFields.get(fieldIndex + 1)), grid.getRowIndex(listFields.get(fieldIndex + 1)));
                        }
                        break;
                    case "2":
                        if (fieldIndex + 1 == listFields.size() || fieldIndex + 2 == listFields.size()) {
                            grid.getChildren().remove(pawn);
                            grid.add(pawn, 6, 1);
                            setFirstPlayersCounter(getFirstPlayersCounter() + 1);
                        } else if (PawnsChecker.check(listPawn, grid, listFields.get(fieldIndex + 2))) {
                            grid.getChildren().remove(pawn);
                            grid.add(pawn, grid.getColumnIndex(listFields.get(fieldIndex + 2)), grid.getRowIndex(listFields.get(fieldIndex + 2)));
                        }
                        break;
                    case "3":
                        if (fieldIndex + 1 == listFields.size() || fieldIndex + 2 == listFields.size() || fieldIndex + 3 == listFields.size()) {
                            grid.getChildren().remove(pawn);
                            grid.add(pawn, 6, 1);
                            setFirstPlayersCounter(getFirstPlayersCounter() + 1);
                        } else if (PawnsChecker.check(listPawn, grid, listFields.get(fieldIndex + 3))) {
                            grid.getChildren().remove(pawn);
                            grid.add(pawn, grid.getColumnIndex(listFields.get(fieldIndex + 3)), grid.getRowIndex(listFields.get(fieldIndex + 3)));
                        }
                        break;
                    case "4":
                        if (fieldIndex + 1 == listFields.size() || fieldIndex + 2 == listFields.size() || fieldIndex + 3 == listFields.size() || fieldIndex + 4 == listFields.size()) {
                            grid.getChildren().remove(pawn);
                            grid.add(pawn, 6, 1);
                            setFirstPlayersCounter(getFirstPlayersCounter() + 1);
                        } else if (PawnsChecker.check(listPawn, grid, listFields.get(fieldIndex + 4))) {
                            grid.getChildren().remove(pawn);
                            grid.add(pawn, grid.getColumnIndex(listFields.get(fieldIndex + 4)), grid.getRowIndex(listFields.get(fieldIndex + 4)));
                        }
                        break;

                }

            });


        }
    }
}
