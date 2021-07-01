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

                Pawn blackPawn = new Pawn().createPawn(BLACK_TEXT);
                Key key = new Key();


                int i = Integer.parseInt(dice.getText());

                if(!PawnsChecker.checkIfBlackPawnIsOnField(blackPawnOnBoardMap, i)|| blackPawnOnBoardMap.isEmpty()){
                    key.setKey(key.getKey()+i);
                    blackPawn.setOnAction(event1 -> {
                        if (diceRolled && activePlayer.equals(PLAYER_1_TEXT)) {
                            handleBlackPawnMove(key, counter, grid, firstUser,blackPawn, dice, whitePawnOnBoardMap, blackPawnOnBoardMap, secondPlayersPawn);
                        }else if (!activePlayer.equals(PLAYER_1_TEXT)) {
                            error.setText("It's not your move");
                        } else {
                            error.setText("Roll the dice");
                        }
                    });
                    blackPawnOnBoardMap.put(key.getKey(), blackPawn);
                    System.out.println(blackPawnOnBoardMap.containsKey(i));
                    grid.add(blackPawn, GridPane.getColumnIndex(firstUser.get(i - 1)), GridPane.getRowIndex(firstUser.get(i - 1)));
                    info.setText(PLAYER_2_TEXT);
                    activePlayer=PLAYER_2_TEXT;
                    diceRolled=false;

                    System.out.println(key.getKey());
                    counter.setFirstPlayersCounterStart(counter.getFirstPlayersCounterStart() - 1);
                    start1.setText(String.valueOf(counter.getFirstPlayersCounterStart()));
                    if (counter.getFirstPlayersCounterStart() == 0) {
                        firstPlayersPawn.setDisable(true);
                    }
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

                Key key = new Key();
                Pawn whitePawn = new Pawn().createPawn(WHITE_TEXT);


                int i = Integer.parseInt(dice.getText());

                if(!PawnsChecker.checkIfWhitePawnIsOnField(whitePawnOnBoardMap, i)||whitePawnOnBoardMap.isEmpty()){
                    key.setKey(key.getKey()+i);
                    whitePawnOnBoardMap.put(key.getKey(), whitePawn);
                    System.out.println(whitePawnOnBoardMap.containsKey(i));
                    whitePawn.setOnAction(event1 -> {
                        if (diceRolled&&activePlayer.equals(PLAYER_2_TEXT)) {
                            handleWhitePawnMove(key, counter, grid, secondUser,whitePawn,dice,whitePawnOnBoardMap, blackPawnOnBoardMap,firstPlayersPawn);
                        } else if(!activePlayer.equals(PLAYER_2_TEXT)) {
                            error.setText("It's not your move");
                        } else {
                            error.setText("Roll the dice");
                        }
                    });
                    grid.add(whitePawn, GridPane.getColumnIndex(secondUser.get(i - 1)), GridPane.getRowIndex(secondUser.get(i - 1)));
                    info.setText(PLAYER_1_TEXT);
                    activePlayer=PLAYER_1_TEXT;
                    diceRolled=false;
                    System.out.println(key.getKey());
                    counter.setSecondPlayersCounterStart(counter.getSecondPlayersCounterStart() - 1);
                    start2.setText(String.valueOf(counter.getSecondPlayersCounterStart()));
                    if (counter.getSecondPlayersCounterStart() == 0) {
                        secondPlayersPawn.setDisable(true);
                    }
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

    private void handleWhitePawnMove(Key key, Counter counter, GridPane grid, List<Rectangle> fieldsList, Button whitePawn, Button dice, Map<Integer, Button> whitePawnOnBoardMap, Map<Integer, Button> blackPawnOnBoardMap, Pawn firstPlayersPawn){
        error.setText("");

        int indexOfFieldWithWhitePawn = key.getKey();

        int i = Integer.parseInt(dice.getText());
        int newFieldIndex = indexOfFieldWithWhitePawn+i;


        if(PawnsChecker.checkIfWhitePawnIsOnField(whitePawnOnBoardMap, newFieldIndex)){
            info.setText("Choose different pawn");
        } else if (indexOfFieldWithWhitePawn + i>fieldsList.size()){
            grid.getChildren().remove(whitePawn);
            whitePawnOnBoardMap.remove(newFieldIndex, whitePawn);
            grid.add(whitePawn, 6, 3);
            whitePawn.setDisable(true);
            counter.setSecondPlayersCounterEnd(counter.getSecondPlayersCounterEnd() + 1);
            end2.setText(String.valueOf(counter.getSecondPlayersCounterEnd()));
            info.setText(PLAYER_1_TEXT);
            diceRolled=false;
            activePlayer=PLAYER_1_TEXT;
        } else if (blackPawnOnBoardMap.containsKey(newFieldIndex)){
            Button blackPawn = OpponentsPawnReturner.getBlackPawnToReturn(newFieldIndex, blackPawnOnBoardMap);
            grid.getChildren().remove(blackPawn);
            blackPawnOnBoardMap.remove(newFieldIndex, blackPawn);
            whitePawnOnBoardMap.remove(key.getKey(), whitePawn);
            key.setKey(newFieldIndex);
            whitePawnOnBoardMap.put(key.getKey(), whitePawn);
            grid.add(whitePawn, GridPane.getColumnIndex(fieldsList.get(newFieldIndex)), GridPane.getRowIndex(fieldsList.get(newFieldIndex)));
            info.setText(PLAYER_1_TEXT);
            diceRolled=false;
            activePlayer=PLAYER_1_TEXT;
            counter.setFirstPlayersCounterStart(counter.getFirstPlayersCounterStart() + 1);
            start1.setText(String.valueOf(counter.getFirstPlayersCounterStart()));
            if(counter.getFirstPlayersCounterStart()>0&&firstPlayersPawn.isDisabled()){
                firstPlayersPawn.setDisable(false);
            }
        } else {
            grid.getChildren().remove(whitePawn);
            whitePawnOnBoardMap.remove(key.getKey(), whitePawn);
            key.setKey(newFieldIndex);
            whitePawnOnBoardMap.put(key.getKey(), whitePawn);
            grid.add(whitePawn, GridPane.getColumnIndex(fieldsList.get(newFieldIndex)), GridPane.getRowIndex(fieldsList.get(newFieldIndex)));
            info.setText(PLAYER_1_TEXT);
            diceRolled=false;
            activePlayer=PLAYER_1_TEXT;
        }

    }

    private void handleBlackPawnMove(Key key, Counter counter, GridPane grid, List<Rectangle> fieldsList, Button blackPawn, Button dice, Map<Integer, Button> whitePawnOnBoardMap, Map<Integer, Button> blackPawnOnBoardMap, Pawn secondPlayersPawn) {
        error.setText("");

        int indexOfFieldWithBlackPawn = key.getKey();

        int i = Integer.parseInt(dice.getText());
        int newFieldIndex = indexOfFieldWithBlackPawn+i;

        if(PawnsChecker.checkIfWhitePawnIsOnField(whitePawnOnBoardMap, newFieldIndex)){
            info.setText("Choose different pawn");
        } else if (indexOfFieldWithBlackPawn + i>fieldsList.size()){
            grid.getChildren().remove(blackPawn);
            blackPawnOnBoardMap.remove(newFieldIndex, blackPawn);
            grid.add(blackPawn, 6, 3);
            blackPawn.setDisable(true);
            counter.setFirstPlayersCounterStart(counter.getFirstPlayersCounterEnd() + 1);
            end2.setText(String.valueOf(counter.getFirstPlayersCounterEnd()));
            info.setText(PLAYER_2_TEXT);
            diceRolled=false;
            activePlayer=PLAYER_2_TEXT;
        } else if (whitePawnOnBoardMap.containsKey(newFieldIndex)){
            Button whitePawn = OpponentsPawnReturner.getWhitePawnToReturn(newFieldIndex, whitePawnOnBoardMap);
            grid.getChildren().remove(whitePawn);
            whitePawnOnBoardMap.remove(newFieldIndex, whitePawn);
            grid.getChildren().remove(blackPawn);
            blackPawnOnBoardMap.remove(key.getKey(), blackPawn);
            key.setKey(newFieldIndex);
            blackPawnOnBoardMap.put(key.getKey(), blackPawn);
            grid.add(blackPawn, GridPane.getColumnIndex(fieldsList.get(newFieldIndex)), GridPane.getRowIndex(fieldsList.get(newFieldIndex)));
            info.setText(PLAYER_2_TEXT);
            diceRolled=false;
            activePlayer=PLAYER_2_TEXT;
            counter.setSecondPlayersCounterStart(counter.getSecondPlayersCounterStart() + 1);
            start1.setText(String.valueOf(counter.getSecondPlayersCounterStart()));
            if(counter.getSecondPlayersCounterStart()>0&&secondPlayersPawn.isDisabled()){
                secondPlayersPawn.setDisable(false);
            }
        } else {
            grid.getChildren().remove(blackPawn);
            blackPawnOnBoardMap.remove(key.getKey(), blackPawn);
            key.setKey(newFieldIndex);
            blackPawnOnBoardMap.put(key.getKey(), blackPawn);
            grid.add(blackPawn, GridPane.getColumnIndex(fieldsList.get(newFieldIndex)), GridPane.getRowIndex(fieldsList.get(newFieldIndex)));
            info.setText(PLAYER_2_TEXT);
            diceRolled=false;
            activePlayer=PLAYER_2_TEXT;
        }
    }

}
