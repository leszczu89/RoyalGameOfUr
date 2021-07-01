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

import java.util.*;


public class TheRoyalGameOfUr extends Application {


    public static final String PLAYER_1_TEXT = "player 1";
    public static final String PLAYER_2_TEXT = "player 2";
    public static final String WHITE_TEXT = "white";
    public static final String BLACK_TEXT = "black";

    public static void main (String[] args) {launch(args);}

    private final Label player1 = new Label("Player 1");
    private final Label start1 = new Label("7");
    private final Label end1 = new Label("0");
    private final Label player2 = new Label("Player 2");
    private final Label start2 = new Label("7");
    private final Label end2 = new Label("0");
    private final Label info = new Label(PLAYER_1_TEXT);
    private final Label error = new Label("");
    private boolean diceRolled = false;
    private String activePlayer = "player 1";
    List<Pawn> firstPlayersPawns = new ArrayList<>();
    List<Pawn> secondPlayersPawns = new ArrayList<>();

    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 1600, 900);

        scene.getStylesheets().add(FileUtil.getFilePath("/StyleSheets/primaryStageStyle.css"));
        //Creating Background
        Image image = new Image(FileUtil.getFilePath("/Images/background.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        borderPane.setBackground(background);

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
        helpMenu.getItems().forEach(menuItem -> menuItem.getStyleClass().add("menu"));

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
        Dice diceCreator = new Dice();
        Button dice = diceCreator.createDiceButton();
        grid.add(dice, 8, 4);

        Random random = new Random();

        dice.setId("");
        dice.setOnAction(event -> {
           error.setText("");
            dice.setText(String.valueOf(random.nextInt(5)));
            if(activePlayer.equals(PLAYER_1_TEXT)) {
                if (dice.getText().equals("0")) {
                    error.setText("Lost");
                    diceRolled=false;
                    activePlayer=PLAYER_2_TEXT;
                    info.setText(PLAYER_2_TEXT);
                } else {
                    diceRolled=true;
                }
            }else if (activePlayer.equals(PLAYER_2_TEXT)){
                if(dice.getText().equals("0")) {
                    error.setText("Lost");
                    diceRolled=false;
                    activePlayer=PLAYER_1_TEXT;
                    info.setText(PLAYER_1_TEXT);
                } else {
                    diceRolled=true;
                }
            }
        });
        //Win info
        if(counter.getFirstPlayersCounterStart()==0&&counter.getFirstPlayersCounterEnd()==7){
            info.setText("Winner: "+PLAYER_1_TEXT);
        } else if(counter.getSecondPlayersCounterStart()==0&&counter.getSecondPlayersCounterEnd()==7){
            info.setText("Winner: "+ PLAYER_2_TEXT);
        }

        //Users fields
        UsersFieldCreator firstCreator = new UsersFieldCreator();
        List<Rectangle> firstUser = firstCreator.getFirstUser();
        List<Rectangle> secondUser = firstCreator.getSecondUser();
        System.out.println(firstUser.size());
        System.out.println(secondUser.size());



        //Adding first pawns
        Pawn pawnCreator = new Pawn();
        Pawn firstPlayersPawn = pawnCreator.createPawn(BLACK_TEXT);
        Pawn secondPlayersPawn = pawnCreator.createPawn(WHITE_TEXT);
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

        Map<Integer, Button> blackPawnOnBoardMap = new HashMap<>();
        Map<Integer, Button> whitePawnOnBoardMap = new HashMap<>();

        //Action handler for first pawn - first player
        firstPlayersPawn.setOnAction(event -> {
            error.setText("");

            if (diceRolled&&activePlayer.equals(PLAYER_1_TEXT)) {

                Pawn pawn = pawnCreator.createPawn(BLACK_TEXT);
                pawn.setOnAction(event1 -> {
                    if (diceRolled && activePlayer.equals(PLAYER_1_TEXT)) {
                        handle(PLAYER_2_TEXT, pawn, firstUser, dice, grid, counter, firstPlayersPawns, secondPlayersPawns, firstPlayersPawn, secondPlayersPawn);
                    } else if (!activePlayer.equals(PLAYER_1_TEXT)) {
                        error.setText("It's not your move");
                    } else {
                        error.setText("Roll the dice");
                    }
                });

                int i = Integer.parseInt(dice.getText());

                if (!PawnsChecker.checkIfPawnIsOnField(firstPlayersPawns, firstUser.get(i - 1)) || firstPlayersPawns.isEmpty()) {
                    grid.add(pawn, GridPane.getColumnIndex(firstUser.get(i - 1)), GridPane.getRowIndex(firstUser.get(i - 1)));
                    info.setText(PLAYER_2_TEXT);
                    activePlayer=PLAYER_2_TEXT;
                    diceRolled=false;
                    firstPlayersPawns.add(pawn);
                    counter.setFirstPlayersCounterStart(counter.getFirstPlayersCounterStart() - 1);
                    start1.setText(String.valueOf(counter.getFirstPlayersCounterStart()));
                    if (counter.getFirstPlayersCounterStart() == 0) {
                        firstPlayersPawn.setDisable(true);
                    }
                    System.out.println(firstPlayersPawns.size());
                    System.out.println(secondPlayersPawns.size());
                    System.out.println(firstUser.size());
                    System.out.println(secondUser.size());
                } else {
                    error.setText("Choose different pawn");
                }
            } else if (!diceRolled) {
                error.setText("Roll the dice");
            } else {
                error.setText("It's not your move");
            }
        });

        //Action handler for first pawn - second player
        secondPlayersPawn.setOnAction(event -> {
            error.setText("");
            if (activePlayer.equals(PLAYER_2_TEXT)&&diceRolled) {

                Pawn pawn = pawnCreator.createPawn(WHITE_TEXT);
                pawn.setOnAction(event1 -> {
                    if (diceRolled&&activePlayer.equals(PLAYER_2_TEXT)) {
                        handle(PLAYER_1_TEXT, pawn, secondUser, dice, grid, counter, secondPlayersPawns, firstPlayersPawns, firstPlayersPawn, secondPlayersPawn);
                    } else if(!activePlayer.equals(PLAYER_2_TEXT)) {
                        error.setText("It's not your move");
                    } else {
                        error.setText("Roll the dice");
                    }
                });

                int i = Integer.parseInt(dice.getText());

                if (!PawnsChecker.checkIfPawnIsOnField(secondPlayersPawns, secondUser.get(i - 1)) || secondPlayersPawns.isEmpty()) {
                    grid.add(pawn, GridPane.getColumnIndex(secondUser.get(i - 1)), GridPane.getRowIndex(secondUser.get(i - 1)));
                    info.setText(PLAYER_1_TEXT);
                    activePlayer=PLAYER_1_TEXT;
                    diceRolled=false;
                    secondPlayersPawns.add(pawn);
                    counter.setSecondPlayersCounterStart(counter.getSecondPlayersCounterStart() - 1);
                    start2.setText(String.valueOf(counter.getSecondPlayersCounterStart()));
                    if (counter.getSecondPlayersCounterStart() == 0) {
                        secondPlayersPawn.setDisable(true);
                    }
                    System.out.println(firstPlayersPawns.size());
                    System.out.println(secondPlayersPawns.size());
                    System.out.println(firstUser.size());
                    System.out.println(secondUser.size());
                } else {
                    error.setText("Choose different pawn");
                }
            } else if(!activePlayer.equals(PLAYER_2_TEXT)) {
                error.setText("It's not your move");
            } else {
                error.setText("Roll the dice");
            }
        });

        primaryStage.setTitle("The Royal Game of Ur");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }



    private void handle(String switchPlayer, Pawn pawn, List<Rectangle> fieldsList, Button dice, GridPane grid, Counter counter, List<Pawn> pawnsList, List<Pawn> opponentPawnsList, Pawn firstPlayersPawn, Pawn secondPlayersPawn) {
        error.setText("");
        Rectangle fieldWithPawn = new Rectangle();

        for (Rectangle field : fieldsList) {
            if (GridPane.getColumnIndex(pawn).equals(GridPane.getColumnIndex(field)) && GridPane.getRowIndex(pawn).equals(GridPane.getRowIndex(field))) {
                fieldWithPawn = field;
            }
        }
        int fieldIndex = fieldsList.indexOf(fieldWithPawn);

        int i = Integer.parseInt(dice.getText());
        if (fieldIndex + i -1> fieldsList.size()) {

            if (pawn.getId().equals(BLACK_TEXT)) {
                grid.getChildren().remove(pawn);
                grid.add(pawn, 6, 1);
                pawn.setDisable(true);
                counter.setFirstPlayersCounterEnd(counter.getFirstPlayersCounterEnd() + 1);
                end1.setText(String.valueOf(counter.getFirstPlayersCounterEnd()));
                System.out.println(firstPlayersPawns.size());
                System.out.println(secondPlayersPawns.size());
            } else if (pawn.getId().equals(WHITE_TEXT)) {
                grid.getChildren().remove(pawn);
                grid.add(pawn, 6, 3);
                pawn.setDisable(true);
                counter.setSecondPlayersCounterEnd(counter.getSecondPlayersCounterEnd() + 1);
                end2.setText(String.valueOf(counter.getSecondPlayersCounterEnd()));
                System.out.println(firstPlayersPawns.size());
                System.out.println(secondPlayersPawns.size());
            }
            activePlayer=switchPlayer;
            info.setText(switchPlayer);
            diceRolled=false;
        } else if (PawnsChecker.checkIfPawnIsOnField(pawnsList, fieldsList.get(fieldIndex + i))) {
            error.setText("Choose different pawn");
        } else if (PawnsChecker.checkIfPawnIsOnField(opponentPawnsList, fieldsList.get(fieldIndex + i))) {
            Pawn opponentsPawn = OpponentsPawnReturner.getOpponentsPawnToReturn(i, opponentPawnsList, fieldsList, fieldIndex);

            if (opponentsPawn.getId().equals(WHITE_TEXT)) {
                counter.setSecondPlayersCounterStart(counter.getSecondPlayersCounterStart() + 1);
                start2.setText(String.valueOf(counter.getSecondPlayersCounterStart()));
                if (counter.getSecondPlayersCounterStart()>0&&secondPlayersPawn.isDisabled()){
                    secondPlayersPawn.setDisable(false);
                }
                grid.getChildren().remove(opponentsPawn);
                opponentPawnsList.remove(opponentsPawn);
                System.out.println(firstPlayersPawns.size());
                System.out.println(secondPlayersPawns.size());
            } else if (opponentsPawn.getId().equals(BLACK_TEXT)) {
                counter.setFirstPlayersCounterStart(counter.getFirstPlayersCounterStart() + 1);
                start1.setText(String.valueOf(counter.getFirstPlayersCounterStart()));
                if(counter.getFirstPlayersCounterStart()>0&&firstPlayersPawn.isDisabled()){
                    firstPlayersPawn.setDisable(false);
                }
                grid.getChildren().remove(opponentsPawn);
                opponentPawnsList.remove(opponentsPawn);
                System.out.println(firstPlayersPawns.size());
                System.out.println(secondPlayersPawns.size());
            }

            grid.add(pawn, GridPane.getColumnIndex(fieldsList.get(fieldIndex + i)), GridPane.getRowIndex(fieldsList.get(fieldIndex + i)));
            info.setText(switchPlayer);
            activePlayer=switchPlayer;
            diceRolled=false;
        } else if (!PawnsChecker.checkIfPawnIsOnField(pawnsList, fieldsList.get(fieldIndex + i))) {
            grid.getChildren().remove(pawn);
            grid.add(pawn, GridPane.getColumnIndex(fieldsList.get(fieldIndex + i)), GridPane.getRowIndex(fieldsList.get(fieldIndex + i)));
            info.setText(switchPlayer);
            diceRolled=false;
            activePlayer=switchPlayer;
            System.out.println(firstPlayersPawns.size());
            System.out.println(secondPlayersPawns.size());
        }

    }

}


