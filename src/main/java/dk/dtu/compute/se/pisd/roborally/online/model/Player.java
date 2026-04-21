package dk.dtu.compute.se.pisd.roborally.online.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        scope=Player.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "uid")
public class Player {

    private long uid;

    private String name;

    private Game game;

    // ...

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    @Override
    public String toString() {
        return "Player{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", game=" + (game != null ? game.getName() : "<none>") +
                '}';
    }

}
