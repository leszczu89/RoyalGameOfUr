package com.kodilla;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class TheRoyalGameOfUr extends Application {

    private final Image imageBack = new Image("file:src/main/resources/Images/background.jpg");

    private static int firstPlayersCounterLeft = 7;
    private static int secondPlayersCounterLeft = 7;
    private static int firstPlayersCounter = 0;
    private static int secondPlayersCounter = 0;



    public static int getFirstPlayersCounterLeft() {
        return firstPlayersCounterLeft;
    }

    public static void setFirstPlayersCounterLeft(int firstPlayersCounterLeft) {
        TheRoyalGameOfUr.firstPlayersCounterLeft = firstPlayersCounterLeft;
    }

    public static int getSecondPlayersCounterLeft() {
        return secondPlayersCounterLeft;
    }

    public static void setSecondPlayersCounterLeft(int secondPlayersCounterLeft) {
        TheRoyalGameOfUr.secondPlayersCounterLeft = secondPlayersCounterLeft;
    }

    public static int getFirstPlayersCounter() {
        return firstPlayersCounter;
    }

    public static void setFirstPlayersCounter(int firstPlayersCounter) {
        TheRoyalGameOfUr.firstPlayersCounter = firstPlayersCounter;
    }

    public static int getSecondPlayersCounter() {
        return secondPlayersCounter;
    }

    public static void setSecondPlayersCounter(int secondPlayersCounter) {
        TheRoyalGameOfUr.secondPlayersCounter = secondPlayersCounter;
    }

    public static void main (String[] args) {launch(args);
    }
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 1600, 900);
        scene.getStylesheets().add("file:src/main/java/com/kodilla/StylesheetFirst.css");

        //Adding board
        BoardCreator boardCreator = new BoardCreator();
        borderPane.setCenter(boardCreator.boardCreator());
        GridPane grid = BoardCreator.getGrid();

        Label player1 = new Label("Player 1");
        Label start1 = new Label("Start: " + getFirstPlayersCounterLeft());
        Label end1 = new Label("End: " + getFirstPlayersCounter());
        Label player2 = new Label("Player 2");
        Label start2 = new Label("Start: " + getSecondPlayersCounterLeft());
        Label end2 = new Label("End: " + getSecondPlayersCounter());

        grid.add(player1, 4, 0);
        grid.add(start1, 5, 0);
        grid.add(end1, 6, 0);
        grid.add(player2, 4, 4);
        grid.add(start2, 5, 4);
        grid.add(end2, 6, 4);

        //Adding info
        Label info = new Label();
        info.setText("player 1");
        Label error = new Label();
        error.setText("");
        grid.add(info, 0, 4, 2,1);
        grid.add(error, 0, 5, 2,1);

        //Adding dice
        Button dice = Dice.diceCreator(grid);
        grid.add(dice, 8, 4);

        Random random = new Random();
        dice.setOnAction(event -> {
           error.setText("");
            dice.setText(String.valueOf(random.nextInt(5)));
            if(info.getText().equals("player 1")){
                if(dice.getText().equals("0")) {
                    info.setText("player 2");
                    error.setText("Lost");
                }else if (info.getText().equals("player 2")){
                    if(dice.getText().equals("0")) {
                        info.setText("player 1");
                        error.setText("Lost");
                    }
                }
            }
        });
        //Users fields
        FirstUsersFieldsCreator firstCreator = new FirstUsersFieldsCreator();
        ArrayList<Rectangle> firstUser = firstCreator.firstUsersFieldsCreator();
        SecondUsersFieldsCreator secondCreator = new SecondUsersFieldsCreator();
        ArrayList<Rectangle> secondUser = secondCreator.secondUsersFieldsCreator();

        //List of first users pawns on board
        ArrayList<Node> firstPlayersPawns = new ArrayList<>();
 //       ObservableList<Node> firstPlayersList = FXCollections.observableList(firstPlayersPawns);

        //List of second users pawns on board
        ArrayList<Node> secondPlayersPawns = new ArrayList<>();
//        ObservableList<Node> secondPlayersList = FXCollections.observableList(secondPlayersPawns);

        //Adding first pawns
        PawnCreator pawnCreator = new PawnCreator();
        Button firstPlayersPawn = pawnCreator.pawnCreator(grid, "black");
        Button secondPlayersPawn = pawnCreator.pawnCreator(grid, "white");

        grid.add(firstPlayersPawn, 5, 1);
        grid.add(secondPlayersPawn, 5, 3);

        //Action handler for first pawn - first player
        firstPlayersPawn.setOnAction(event -> {
            if (getFirstPlayersCounterLeft()>0&&info.getText().equals("player 1")){
                Node node = pawnCreator.pawnCreator(grid, "black");
                switch (dice.getText()){
                    case "1":
                        if (PawnsChecker.check(firstPlayersPawns, grid, firstUser.get(0)) ||firstPlayersPawns.isEmpty()) {
                            grid.add(node, grid.getColumnIndex(firstUser.get(0)), grid.getRowIndex(firstUser.get(0)));
                            info.setText("player 2");
                            firstPlayersPawns.add(node);
                            setFirstPlayersCounterLeft(getFirstPlayersCounterLeft()-1);
                        } else {
                            error.setText("Choose different pawn");
                        }
                        break;
                    case "2":
                        if (PawnsChecker.check(firstPlayersPawns, grid, firstUser.get(1))||firstPlayersPawns.isEmpty()) {
                            grid.add(node, grid.getColumnIndex(firstUser.get(1)), grid.getRowIndex(firstUser.get(1)));
                            info.setText("player 2");
                            firstPlayersPawns.add(node);
                            setFirstPlayersCounterLeft(getFirstPlayersCounterLeft()-1);
                        }else {
                            error.setText("Choose different pawn");
                        }
                        break;
                    case "3":
                        if (PawnsChecker.check(firstPlayersPawns, grid, firstUser.get(2))||firstPlayersPawns.isEmpty()) {
                            grid.add(node, grid.getColumnIndex(firstUser.get(2)), grid.getRowIndex(firstUser.get(2)));
                            info.setText("player 2");
                            firstPlayersPawns.add(node);
                            setFirstPlayersCounterLeft(getFirstPlayersCounterLeft()-1);
                        }else {
                            error.setText("Choose different pawn");
                        }
                        break;
                    case "4":
                        if (PawnsChecker.check(firstPlayersPawns, grid, firstUser.get(3))||firstPlayersPawns.isEmpty()) {
                            grid.add(node, grid.getColumnIndex(firstUser.get(3)), grid.getRowIndex(firstUser.get(3)));
                            info.setText("player 2");
                            firstPlayersPawns.add(node);
                            setFirstPlayersCounterLeft(getFirstPlayersCounterLeft()-1);
                        }else {
                            error.setText("Choose different pawn");
                        }
                        break;
                }
                if (getFirstPlayersCounterLeft()==0){
                    firstPlayersPawn.setVisible(false);
                }
            } else {
                error.setText("It's not your move");
            }
        });
        //Action handler for first pawn - second player
        secondPlayersPawn.setOnAction(event -> {
            if (getSecondPlayersCounterLeft()>0&&info.getText().equals("player 2")){
                Node node = pawnCreator.pawnCreator(grid, "white");
                switch (dice.getText()){
                    case "1":
                        if (PawnsChecker.check(secondPlayersPawns, grid, secondUser.get(0)) ||secondPlayersPawns.isEmpty()) {
                            grid.add(node, grid.getColumnIndex(secondUser.get(0)), grid.getRowIndex(secondUser.get(0)));
                            info.setText("player 1");
                            secondPlayersPawns.add(node);
                            secondPlayersCounterLeft--;;
                        } else {
                            error.setText("Choose different pawn");
                        }
                        break;
                    case "2":
                        if (PawnsChecker.check(secondPlayersPawns, grid, secondUser.get(1))||secondPlayersPawns.isEmpty()) {
                            grid.add(node, grid.getColumnIndex(secondUser.get(1)), grid.getRowIndex(secondUser.get(1)));
                            info.setText("player 1");
                            secondPlayersPawns.add(node);
                            secondPlayersCounterLeft--;;
                        }else {
                            error.setText("Choose different pawn");
                        }
                        break;
                    case "3":
                        if (PawnsChecker.check(secondPlayersPawns, grid, secondUser.get(2))||secondPlayersPawns.isEmpty()) {
                            grid.add(node, grid.getColumnIndex(secondUser.get(2)), grid.getRowIndex(secondUser.get(2)));
                            info.setText("player 1");
                            secondPlayersPawns.add(node);
                            secondPlayersCounterLeft--;;
                        }else {
                            error.setText("Choose different pawn");
                        }
                        break;
                    case "4":
                        if (PawnsChecker.check(secondPlayersPawns, grid, secondUser.get(3))||secondPlayersPawns.isEmpty()) {
                            grid.add(node, grid.getColumnIndex(secondUser.get(3)), grid.getRowIndex(secondUser.get(3)));
                            info.setText("player 1");
                            secondPlayersPawns.add(node);
                            secondPlayersCounterLeft--;
                        }else {
                            error.setText("Choose different pawn");
                        }
                        break;
                }
                if (getFirstPlayersCounterLeft()==0){
                    secondPlayersPawn.setVisible(false);
                }
            } else {
                error.setText("It's not your move");
            }
        });

        PawnsEventHandler.handle(firstPlayersPawns, dice, grid, firstUser);
        System.out.println(firstUser.size());
        System.out.println(grid.getColumnIndex(firstUser.get(3)));
        System.out.println(getSecondPlayersCounterLeft());
        System.out.println(getFirstPlayersCounterLeft());

        //Scene
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        borderPane.setBackground(background);
        primaryStage.setTitle("The Royal Game of Ur");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
