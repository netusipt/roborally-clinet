package dk.dtu.compute.se.pisd.roborally.online.view;

import dk.dtu.compute.se.pisd.roborally.online.controller.OnlineController;
import dk.dtu.compute.se.pisd.roborally.online.model.Game;

import dk.dtu.compute.se.pisd.roborally.online.model.Player;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class GamesView extends GridPane {

    public static final int width = 640;
    public static final int height = 400;

    private OnlineController onlineController;

    public GamesView(OnlineController onlineController, GameSelection gameSelection) {
        this.onlineController = onlineController;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.getColumnConstraints().add(new ColumnConstraints(300));
        this.getColumnConstraints().add(new ColumnConstraints(80));
        this.getColumnConstraints().add(new ColumnConstraints(80));
        this.getColumnConstraints().add(new ColumnConstraints(80));
        this.getColumnConstraints().add(new ColumnConstraints(80));

        update();
    }

    private void update() {
        try {
            int i = 0;
            for (Game game: onlineController.onlineState.getOpenGames() ) {

                TextFlow gameInfo = new TextFlow();

                Text gameName = new Text(
                        "Game: " + game.getName() +
                                " (min: " + game.getMinPlayers() +
                                ", max: " + game.getMaxPlayers() + ")" );
                gameInfo.getChildren().add(gameName);

                // TODO Assignment 7c: Update the detailed description of the game
                //      with game owner and  all players who joined.
                for (Player player: game.getPlayers()) {
                    Text playerInfo = new Text("\n  Player " + player.getName()
                            /* + " (" + player.getUser().getName() + ")" */ );
                    gameInfo.getChildren().add(playerInfo);
                }
                gameInfo.setTextAlignment(TextAlignment.LEFT);
                gameInfo.setLineSpacing(1.0);
                gameInfo.setPrefWidth(190);

                Button joinButton = new Button("Join");
                joinButton.setOnAction( e -> {
                    try {
                        onlineController.joinGame(game);
                    } catch (Exception exception) {
                        // probably not needed since joinGame should catch possible exceptions
                    }
                });
                if (game.getPlayers().size() >= game.getMaxPlayers() || onlineController.userInGame(game)) {
                    joinButton.setDisable(true);
                } else {
                    joinButton.setDisable(false);
                }

                Button leaveButton = new Button("Leave");
                leaveButton.setOnAction( e -> {
                    try {
                        onlineController.leaveGame(game);
                    } catch (Exception exception) {
                        // probably not needed since joinGame should catch possible exceptions
                    }
                });
                if (!onlineController.userInGame(game) || onlineController.userOwnsGame(game)) {
                    leaveButton.setDisable(true);
                } else {
                    leaveButton.setDisable(false);
                }

                Button startButton = new Button("Start");
                startButton.setOnAction( e -> onlineController.gameSelected(game) );
                if (game.getMinPlayers() <= game.getPlayers().size() &&
                        game.getMaxPlayers() >= game.getPlayers().size() &&
                        onlineController.userInGame(game)) {
                    startButton.setDisable(false);
                } else {
                    startButton.setDisable(true);
                }

                Button deleteButton = new Button("Delete");
                deleteButton.setOnAction(
                        e -> onlineController.deleteGame(game) );
                if (onlineController.userOwnsGame(game)) {
                    deleteButton.setDisable(false);
                } else {
                    deleteButton.setDisable(true);
                }


                this.add(gameInfo, 0, i);
                this.add(joinButton, 1, i);
                this.add(leaveButton, 2, i);
                this.add(startButton, 3, i);
                this.add(deleteButton, 4, i);
                i++;
            }
        } catch (Exception e) {
            Label text = new Label("There was a problem with loading the games.");
            this.add(text, 0,0);
            e.printStackTrace();

        }
    }

}
