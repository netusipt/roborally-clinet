package dk.dtu.compute.se.pisd.roborally.online.model;

import java.util.List;

public class OnlineState {

    private User signedInUser;

    private List<Game> openGames;

    public User getSignedInUser() {
        return signedInUser;
    }

    public void setSignedInUser(User signedInUser) {
        this.signedInUser = signedInUser;
    }

    public List<Game> getOpenGames() {
        return openGames;
    }

    public void setOpenGames(List<Game> openGames) {
        this.openGames = openGames;
    }

}
