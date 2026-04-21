package dk.dtu.compute.se.pisd.roborally.online.view;

import dk.dtu.compute.se.pisd.roborally.online.controller.OnlineController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class GameSelection extends BorderPane {

    OnlineController onlineController;


    public GameSelection (OnlineController onlineController) {
        this.onlineController = onlineController;

        this.setPrefSize(GamesView.width,GamesView.height);

        Label signedInUser = new Label("You are signed in as " + onlineController.onlineState.getSignedInUser().getName());

        Button createGame = new Button("New Game");
        createGame.setOnAction((e) -> onlineController.createGame());
        // also here, this is a quick hack
        createGame.setMinWidth(50);
        createGame.setMinHeight(30);

        Button refresh = new Button("Refresh");
        refresh.setOnAction((e) -> onlineController.selectGame());
        // also here, this is a quick hack
        refresh.setMinWidth(50);
        refresh.setMinHeight(30);

        Button close = new Button("Close");
        close.setOnAction((e) -> onlineController.gameSelected(null));
        // also here, this is a quick hack
        close.setMinWidth(50);
        close.setMinHeight(30);

        GridPane top = new GridPane();
        top.setPadding(new Insets(5, 5, 5, 5));
        top.getColumnConstraints().add(new ColumnConstraints(300));
        top.getColumnConstraints().add(new ColumnConstraints(80));
        top.getColumnConstraints().add(new ColumnConstraints(80));
        top.getColumnConstraints().add(new ColumnConstraints(80));
        top.getColumnConstraints().add(new ColumnConstraints(80));
        top.add(signedInUser, 0,0);
        top.add(createGame, 1, 0);
        top.add(refresh, 2,0);
        top.add(close,4, 0);

        Pane bottom  = new GamesView(onlineController, this);


        VBox vbox =  new VBox(top, bottom);

        this.getChildren().add(vbox);
    }

}
