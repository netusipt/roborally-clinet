package dk.dtu.compute.se.pisd.roborally.online.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

/*
    See https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
    for information as to why use JsonIdentity information!

    We can either use identity or managed references (and backwards references) to avoid
    deeply copying the object tree. Anyway, we should limit how much data is serialized
    in JSON (in the worst case the complete database is following suit).
 */

@JsonIdentityInfo(
        scope=Game.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "uid")
public class Game {

    private long uid;

    private String name;

    private int minPlayers;

    private int maxPlayers;

    private List<Player> players;

    // Assignment 7c: owner of the game (mirrors backend reference)
    private User owner;

    // Assignment 7e: lifecycle state of the game (SIGNUP / ACTIVE)
    private GameState state = GameState.SIGNUP;

    // TODO There could be more attributes here, kie
    //      in which state is the sign up for the game, did
    //      the game started or finish (after the game started
    //      you might not want new players coming in etc.)
    //      See analogous classes in client.

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

    public List<Player> getPlayers() {
        return players;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Game{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                '}';
    }

}
