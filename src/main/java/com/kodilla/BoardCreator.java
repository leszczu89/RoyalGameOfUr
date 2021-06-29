package com.kodilla;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class BoardCreator {

    private static final Rectangle[] board = new Rectangle[20];

    public Rectangle[] getBoard() {
        return board;
    }

    public GridPane createBoard() {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        for (int i = 0; i<board.length; i++) {
            board[i]=FieldCreator.createField();
        }

        grid.add(board[0], 4, 1);
        grid.add(board[1], 3, 1);
        grid.add(board[2], 2, 1);
        grid.add(board[3], 1, 1);
        grid.add(board[4], 1, 2);
        grid.add(board[5], 2, 2);
        grid.add(board[6], 3, 2);
        grid.add(board[7], 4, 2);
        grid.add(board[8], 5, 2);
        grid.add(board[9], 6, 2);
        grid.add(board[10], 7, 2);
        grid.add(board[11], 8, 2);
        grid.add(board[12], 8, 1);
        grid.add(board[13], 7, 1);
        grid.add(board[14], 4, 3);
        grid.add(board[15], 3, 3);
        grid.add(board[16], 2, 3);
        grid.add(board[17], 1, 3);
        grid.add(board[18], 8, 3);
        grid.add(board[19], 7, 3);

        return grid;
    }
}
