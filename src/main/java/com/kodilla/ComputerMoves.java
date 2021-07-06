package com.kodilla;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.stream.Collectors;

import static com.kodilla.TheRoyalGameOfUr.BLACK_TEXT;

public class ComputerMoves {
    public static void moveComputerPawns(Button dice, List<Pawn> secondPlayersPawns, List<Rectangle> secondUser, Pawn secondPlayersPawn, GridPane grid){
        dice.fire();
        int j = Integer.parseInt(dice.getText());
        List<Pawn> pawnsList = grid.getChildren().stream()
                .filter(node -> node instanceof Pawn)
                .filter(node -> !(GridPane.getColumnIndex(node).equals(5) && GridPane.getRowIndex(node).equals(3)))
                .filter(node -> !(GridPane.getColumnIndex(node).equals(6) && GridPane.getRowIndex(node).equals(3)))
                .map(node -> (Pawn) node)
                .filter(btn -> !btn.getId().equals(BLACK_TEXT))
                .collect(Collectors.toList());

        if(!secondPlayersPawn.isDisable()&&!PawnsChecker.checkIfPawnIsOnField(secondPlayersPawns, secondUser.get(j-1))){
            secondPlayersPawn.fire();
        }else {

            for (Pawn pawn: pawnsList){
                FieldWithPawnIndexReturner indexReturner = new FieldWithPawnIndexReturner();

                int fieldIndex = indexReturner.returnIndexOfFieldWithPawn(secondUser, pawn);

                if (fieldIndex+j>=secondUser.size()) {
                    pawn.fire();
                    break;
                } else if (!PawnsChecker.checkIfPawnIsOnField(pawnsList, secondUser.get(fieldIndex+j))){
                    pawn.fire();
                    break;
                }

            }

        }
    }
}
