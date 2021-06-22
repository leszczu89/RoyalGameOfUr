package com.kodilla;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class FirstUsersFieldsCreator {
    private static ArrayList<Rectangle> list;

    public static ArrayList<Rectangle> getList() {
        return list;
    }

    public ArrayList<Rectangle> firstUsersFieldsCreator() {
        list = new ArrayList<>();

        list.add(BoardCreator.getField1());
        list.add(BoardCreator.getField2());
        list.add(BoardCreator.getField3());
        list.add(BoardCreator.getField4());
        list.add(BoardCreator.getField5());
        list.add(BoardCreator.getField6());
        list.add(BoardCreator.getField7());
        list.add(BoardCreator.getField8());
        list.add(BoardCreator.getField9());
        list.add(BoardCreator.getField10());
        list.add(BoardCreator.getField11());
        list.add(BoardCreator.getField12());
        list.add(BoardCreator.getField13());
        list.add(BoardCreator.getField14());

        return list;
    }
}
