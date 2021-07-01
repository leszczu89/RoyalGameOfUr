package com.kodilla;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SecondaryStage {

    public void secondaryStage() {

        VBox pane = new VBox();
        Scene scene = new Scene(pane, 640, 480);

        scene.getStylesheets().add(FileUtil.getFilePath("/StyleSheets/secondaryStageStyle.css"));

        String text = "Game rules:\n " +
                "-There are two players in the game. " +
                "\n-The main goal is to pass 7 pawns through the board. \n" +
                "-The player who will succeed as the first one - wins.\n" +
                "-You can capture the opponent's pawns in the middle fields\n(they are shared by both players)\n" +
                "-If a pawn is captured - it returns to the beginning of the board\n " +
                "-You move as many fields as you rolled the dice. \n" +
                "-If you get 0 - you lost your turn.";
        Label label = new Label(text);

        label.setWrapText(true);

        Image scheme = new Image(FileUtil.getFilePath("/Images/scheme.png"));
        ImageView image = new ImageView(scheme);
        image.setFitWidth(400);
        image.setFitHeight(200);
        pane.getChildren().add(image);

        pane.getChildren().addAll(label);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5));

        Stage secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setScene(scene);
        secondaryStage.setTitle("Instruction");
        secondaryStage.showAndWait();

    }
}
