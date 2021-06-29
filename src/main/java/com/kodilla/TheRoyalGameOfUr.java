package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Random;


public class TheRoyalGameOfUr extends Application {


    public static void main (String[] args) {launch(args);}

    Label player1 = new Label("Player 1");
    Label start1 = new Label("7");
    Label end1 = new Label("0");
    Label player2 = new Label("Player 2");
    Label start2 = new Label("7");
    Label end2 = new Label("0");
    Label info = new Label("player 2");
    Label error = new Label("");

    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 1600, 900);


        //Adding StyleSheet
        try  {
            scene.getStylesheets().add(getClass().getResource("/StyleSheets/primaryStageStyle.css").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Creating Background
        try {
            Image image = new Image(getClass().getResource("/Images/background.jpg").toString());
            //Scene
            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background background = new Background(backgroundImage);
            borderPane.setBackground(background);

            System.out.println("Reading complete.");
        }
        catch(Exception e) {
            System.out.println("Error: "+e);
        }

        //Adding board
        BoardCreator boardCreator = new BoardCreator();
        GridPane grid = boardCreator.createBoard();
        Text title = new Text("The Royal Game of Ur");
        title.getStyleClass().add("titleText");
        borderPane.setCenter(title);

        //Menu
        Menu menu = new Menu("Settings");
        Menu helpMenu = new Menu("Help");

        ToggleGroup numberOfPlayers = new ToggleGroup();
        RadioMenuItem onePlayer = new RadioMenuItem("1 player");
        RadioMenuItem twoPlayers = new RadioMenuItem("2 players");
        onePlayer.setToggleGroup(numberOfPlayers);
        twoPlayers.setToggleGroup(numberOfPlayers);
        menu.getItems().addAll(onePlayer, twoPlayers);

        menu.getItems().add(new SeparatorMenuItem());

        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(event -> {
            borderPane.getChildren().remove(title);
            borderPane.setCenter(grid);
        });

        menu.getItems().add(new SeparatorMenuItem());
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((event) -> primaryStage.fireEvent(
                new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST)));

        menu.getItems().addAll(newGame, exit);
        menu.getItems().stream().sequential().forEach(menuItem -> menuItem.getStyleClass().add("menu"));

        MenuItem instruction = new MenuItem("Instruction");
        instruction.setOnAction(event -> {
            SecondaryStage secondaryStage = new SecondaryStage();
            secondaryStage.secondaryStage();
        });
        helpMenu.getItems().add(instruction);
        helpMenu.getItems().stream().sequential().forEach(menuItem -> menuItem.getStyleClass().add("menu"));

        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("menu");
        menuBar.getMenus().addAll(menu, helpMenu);

        borderPane.setLeft(menuBar);
        //Counter
        Counter counter = new Counter(7,7,0,0);

        grid.add(player1, 4, 0);
        grid.add(start1, 5, 0);
        grid.add(end1, 6, 0);
        grid.add(player2, 4, 4);
        grid.add(start2, 5, 4);
        grid.add(end2, 6, 4);

        //Adding info
        grid.add(info, 0, 4, 2,1);
        grid.add(error, 0, 5, 3,1);


        //Adding dice
        Button dice = Dice.diceCreator(grid);
        grid.add(dice, 8, 4);

        Random random = new Random();

        dice.setId("");
        dice.setOnAction(event -> {
           error.setText("");
            dice.setText(String.valueOf(random.nextInt(5)));
            if(info.getText().equals("player 1")) {
                if (dice.getText().equals("0")) {
                    info.setText("player 2");
                    error.setText("Lost");
                    dice.setId("player 2");
                } else {
                    dice.setId("player 1");
                }
            }else if (info.getText().equals("player 2")){
                if(dice.getText().equals("0")) {
                    info.setText("player 1");
                    error.setText("Lost");
                    dice.setId("player 1");
                } else {
                    dice.setId("player 2");
                }
            }
        });
        //Win info
        if(counter.getFirstPlayersCounterStart()==0&&counter.getFirstPlayersCounterEnd()==7){
            info.setText("Winner: player 1");
        } else if(counter.getSecondPlayersCounterStart()==0&&counter.getSecondPlayersCounterEnd()==7){
            info.setText("Winner: player 2");
        }

        //Users fields
        UsersFieldCreator firstCreator = new UsersFieldCreator();
        ArrayList<Rectangle> firstUser = firstCreator.getFirstUser();
        ArrayList<Rectangle> secondUser = firstCreator.getSecondUser();

        //List of first users pawns on board
        ArrayList<Pawn> firstPlayersPawns = new ArrayList<>();

        //List of second users pawns on board
        ArrayList<Pawn> secondPlayersPawns = new ArrayList<>();

        //Adding first pawns
        Pawn pawnCreator = new Pawn();
        Pawn firstPlayersPawn = pawnCreator.createPawn("black");
        Pawn secondPlayersPawn = pawnCreator.createPawn("white");
        if (counter.getSecondPlayersCounterStart() == 0) {
            secondPlayersPawn.setDisable(true);
        } else if (counter.getSecondPlayersCounterStart()>0&&secondPlayersPawn.isDisabled()){
            secondPlayersPawn.setDisable(false);
        }
        if (counter.getFirstPlayersCounterStart() == 0) {
            firstPlayersPawn.setDisable(true);
        } else if(counter.getFirstPlayersCounterStart()>0&&firstPlayersPawn.isDisabled()){
            firstPlayersPawn.setDisable(false);
        }

        grid.add(firstPlayersPawn, 5, 1);
        grid.add(secondPlayersPawn, 5, 3);

        //Action handler for first pawn - first player
        firstPlayersPawn.setOnAction(event -> {
            error.setText("");

            if (info.getText().equals("player 1")&&info.getText().equals("player 1")) {

                Pawn pawn = pawnCreator.createPawn("black");

                pawn.setOnAction(event1 -> {
                    if (dice.getId().equals("player 1") && info.getText().equals("player 1")) {
                        handle("player 2", pawn, firstUser, dice, grid, counter, firstPlayersPawns, secondPlayersPawns);
                    } else if (!info.getText().equals("player 1")) {
                        error.setText("It's not your move");
                    } else if (!dice.getId().equals("player 1")) {
                        error.setText("Roll the dice");
                    }
                });

                int i = Integer.parseInt(dice.getText());

                if (!PawnsChecker.check(firstPlayersPawns, firstUser.get(i - 1)) || firstPlayersPawns.isEmpty()) {
                    grid.add(pawn, GridPane.getColumnIndex(firstUser.get(i - 1)), GridPane.getRowIndex(firstUser.get(i - 1)));
                    info.setText("player 2");
                    firstPlayersPawns.add(pawn);
                    counter.setFirstPlayersCounterStart(counter.getFirstPlayersCounterStart() - 1);
                    start1.setText(String.valueOf(counter.getFirstPlayersCounterStart()));
                } else {
                    error.setText("Choose different pawn");
                }
            } else if (!dice.getId().equals("player 1")) {
                error.setText("Roll the dice");
            } else if (!info.getText().equals("player 1")){
                error.setText("It's not your move");
            }
        });

        //Action handler for first pawn - second player
        secondPlayersPawn.setOnAction(event -> {
            error.setText("");
            if (dice.getId().equals("player 2")&&info.getText().equals("player 2")) {

                Pawn pawn = pawnCreator.createPawn("white");

                pawn.setOnAction(event1 -> {
                    if (dice.getId().equals("player 2")&&info.getText().equals("player 2")) {
                        handle("player 1", pawn, secondUser, dice, grid, counter, secondPlayersPawns, firstPlayersPawns);
                    } else if(!info.getText().equals("player 2")) {
                        error.setText("It's not your move");
                    } else if (!dice.getId().equals("player 2")){
                        error.setText("Roll the dice");
                    }
                });

                int i = Integer.parseInt(dice.getText());

                if (!PawnsChecker.check(secondPlayersPawns, secondUser.get(i - 1)) || secondPlayersPawns.isEmpty()) {
                    grid.add(pawn, GridPane.getColumnIndex(secondUser.get(i - 1)), GridPane.getRowIndex(secondUser.get(i - 1)));
                    info.setText("player 1");
                    secondPlayersPawns.add(pawn);
                    counter.setSecondPlayersCounterStart(counter.getSecondPlayersCounterStart() - 1);
                    start2.setText(String.valueOf(counter.getSecondPlayersCounterStart()));
                } else {
                    error.setText("Choose different pawn");
                }
            } else if(!info.getText().equals("player 2")) {
                error.setText("It's not your move");
            } else if (dice.getId().equals("player 2")){
                error.setText("Roll the dice");
            }
        });

        primaryStage.setTitle("The Royal Game of Ur");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void handle(String switchPlayer, Pawn pawn, ArrayList<Rectangle> fieldsList, Button dice, GridPane grid, Counter counter, ArrayList<Pawn> pawnsList, ArrayList<Pawn> opponentPawnsList) {
        error.setText("");
        Rectangle fieldWithPawn = new Rectangle();
        int column = GridPane.getColumnIndex(pawn);
        int row = GridPane.getRowIndex(pawn);

        for (Rectangle field : fieldsList) {
            if (column == GridPane.getColumnIndex(field) && row == GridPane.getRowIndex(field)) {
                fieldWithPawn = field;
            }
        }
        int fieldIndex = fieldsList.indexOf(fieldWithPawn);

        int i = Integer.parseInt(dice.getText());

        if (PawnsChecker.check(pawnsList, fieldsList.get(fieldIndex + i))) {
            info.setText("Choose different pawn");
        } else if (fieldIndex + i >= fieldsList.size()) {

            if (pawn.getId().equals("black")) {
                grid.getChildren().remove(pawn);
                grid.add(pawn, 6, 1);
                pawn.setDisable(true);
                counter.setFirstPlayersCounterEnd(counter.getFirstPlayersCounterEnd() + 1);
                end1.setText(String.valueOf(counter.getFirstPlayersCounterEnd()));
            } else if (pawn.getId().equals("white")) {
                grid.getChildren().remove(pawn);
                grid.add(pawn, 6, 3);
                pawn.setDisable(true);
                counter.setSecondPlayersCounterEnd(counter.getSecondPlayersCounterEnd() + 1);
                end2.setText(String.valueOf(counter.getSecondPlayersCounterEnd()));
            }
            info.setText(switchPlayer);
        } else if (PawnsChecker.check(opponentPawnsList, fieldsList.get(fieldIndex + i))) {
            Pawn opponentsPawn = OpponentsPawnReturner.returnPawn(i, opponentPawnsList, fieldsList, fieldIndex);

            if (opponentsPawn.getId().equals("white")) {
                counter.setSecondPlayersCounterStart(counter.getSecondPlayersCounterStart() + 1);
                start2.setText(String.valueOf(counter.getSecondPlayersCounterStart()));
                grid.getChildren().remove(opponentsPawn);
            } else if (opponentsPawn.getId().equals("black")) {
                counter.setFirstPlayersCounterStart(counter.getFirstPlayersCounterStart() + 1);
                start1.setText(String.valueOf(counter.getFirstPlayersCounterStart()));
                grid.getChildren().remove(opponentsPawn);
            }
            grid.add(pawn, GridPane.getColumnIndex(fieldsList.get(fieldIndex + i)), GridPane.getRowIndex(fieldsList.get(fieldIndex + i)));
            info.setText(switchPlayer);
        } else if (!PawnsChecker.check(pawnsList, fieldsList.get(fieldIndex + i))) {
            grid.getChildren().remove(pawn);
            grid.add(pawn, GridPane.getColumnIndex(fieldsList.get(fieldIndex + i)), GridPane.getRowIndex(fieldsList.get(fieldIndex + i)));
            info.setText(switchPlayer);
        }

    }

}
