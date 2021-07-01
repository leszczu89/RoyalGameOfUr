package com.kodilla;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class UsersFieldCreator {

    private final List<Rectangle> firstUser = new ArrayList<>();
    private final List<Rectangle> secondUser = new ArrayList<>();

    public List<Rectangle> getFirstUser() {
        return firstUser;
    }

    public List<Rectangle> getSecondUser() {
        return secondUser;
    }

    public UsersFieldCreator() {

        BoardCreator boardCreator = new BoardCreator();
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