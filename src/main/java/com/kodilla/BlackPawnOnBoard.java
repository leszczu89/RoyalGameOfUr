package com.kodilla;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BlackPawnOnBoard extends Button{
    private int index = -1;

    public BlackPawnOnBoard createPawnOnBoard() {
        BlackPawnOnBoard blackPawnOnBoard = new BlackPawnOnBoard();
        GridPane.setHalignment(blackPawnOnBoard, HPos.CENTER);
        String color = "black";
        blackPawnOnBoard.setStyle(
                "-fx-background-radius: 5em;-fx-max-width: 50px;-fx-max-height: 50px;-fx-background-color:" + color);
        blackPawnOnBoard.setId(color);

        blackPawnOnBoard.getStylesheets().add(FileUtil.getFilePath("/StyleSheets/primaryStageStyle.css"));

        return blackPawnOnBoard;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
