package com.kodilla;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class UsersFieldCreator {

    private final ArrayList<Rectangle> firstUser = new ArrayList<>();
    private final ArrayList<Rectangle> secondUser = new ArrayList<>();

    public ArrayList<Rectangle> getFirstUser() {
        return firstUser;
    }

    public ArrayList<Rectangle> getSecondUser() {
        return secondUser;
    }
    BoardCreator boardCreator = new BoardCreator();
    public UsersFieldCreator() {

        for (int i = 0; i < 14; i++) {
            firstUser.add(boardCreator.getBoard()[i]);
        }
        for (int i = 14; i < 18; i++) {
            secondUser.add(boardCreator.getBoard()[i]);
        }
        for (int i = 4; i < 12; i++) {
            secondUser.add(boardCreator.getBoard()[i]);
        }
        secondUser.add(boardCreator.getBoard()[18]);
        secondUser.add(boardCreator.getBoard()[19]);

    }

}