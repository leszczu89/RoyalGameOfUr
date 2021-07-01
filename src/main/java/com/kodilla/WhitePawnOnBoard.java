package com.kodilla;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class WhitePawnOnBoard extends Button{
    private int index = -1;

    public WhitePawnOnBoard createPawnOnBoard() {
        WhitePawnOnBoard whitePawnOnBoard = new WhitePawnOnBoard();
        GridPane.setHalignment(whitePawnOnBoard, HPos.CENTER);
        String color = "white";
        whitePawnOnBoard.setStyle(
                "-fx-background-radius: 5em;-fx-max-width: 50px;-fx-max-height: 50px;-fx-background-color:" + color);
        whitePawnOnBoard.setId(color);

        whitePawnOnBoard.getStylesheets().add(FileUtil.getFilePath("/StyleSheets/primaryStageStyle.css"));

        return whitePawnOnBoard;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
