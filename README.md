# RoboRally SignUp Client

This is the **client** project. It talks to the backend at
`http://localhost:8070/roborally/`. Start the backend (`signup4games-backend`)
first, then run this client.

## How to run

1. Start the backend project (`AccessingDataRestApplication.main`).
2. Open this project in IntelliJ and run `StartRoboRally.main`.
3. The default offline RoboRally menu still works; the online flow is reached
   from the **File** menu (Sign Up / Sign In / Select Online Game).

The backend URL is hard-coded to `http://localhost:8070/roborally/` in
`OnlineController.ROBORALLY_BACKEND_URL`.

## Implemented features (Assignments 7a – 7e)

### 7a — Data model
- Bidirectional `Player` ↔ `User` association mirrored in the client model
  (`Player.user`, `User.players`).

### 7b — SignIn, list games, create games
- **Sign In** (`File → Sign In`): looks up the user by name via
  `GET /roborally/user/search?name=…` and uses the returned `User`
  (with backend-assigned `uid`) as the signed-in user.
- **Select Online Game** (`File → Select Online Game`): fetches the list of
  games via `GET /roborally/game` on demand (Refresh button).
- **New Game**: `POST /roborally/game/create` with the configured
  min/max-players (and the signed-in user as owner — see 7c).

### 7c — Owner, join, sign up
- `Game.owner` and `Player.user` added to the client model so the JSON
  returned by the backend deserialises correctly with `@JsonIdentityInfo`.
- `module-info.java` declares `requires java.net.http;`.
- **Create Game** attaches the signed-in user as the game owner before posting,
  so the backend can register them as the first player.
- **Join Game**: `POST /roborally/player` with stub `Game`/`User` (only
  the `uid` is sent) to avoid circular JSON. Backend validates capacity,
  duplicates, and game state.
- `userInGame()` and `userOwnsGame()` enable/disable the Join/Leave/Start/
  Delete buttons appropriately.
- **Sign Up** (`File → Sign Up`): own dialog, `POST /roborally/user`
  to register a new user. AppController and OnlineController have matching
  `signUp()` methods.

### 7d — Leave / delete
- **Leave Game**: client finds the signed-in user's player in the selected
  game and issues `DELETE /roborally/player/{id}`. The backend refuses to
  remove the owner.
- **Delete Game**: owner-only. `DELETE /roborally/game/{id}`. The backend
  cascades player deletion.

### 7e — Start game
- `GameState` enum (`SIGNUP`, `ACTIVE`) added on both sides.
- The `RestClient` is configured with `JdkClientHttpRequestFactory` so PATCH
  requests are supported.
- **Start** (owner-only, when `min ≤ players ≤ max`): PATCHes
  `/roborally/game/{id}` with `{ "state": "ACTIVE" }`, then opens the
  RoboRally board view with the right number of players using their names.
- **Two-step start/play (optional path implemented)**: once a game is
  `ACTIVE`, the Start button is replaced with a **Play** button that any
  joined user can press to open the same board on their own client.
- Join/Leave are disabled (and rejected by the backend) for `ACTIVE` games.

## Project layout

```
src/main/java/dk/dtu/compute/se/pisd/roborally/
├── online/
│   ├── controller/OnlineController.java   ← REST calls + sign-in/up/game flow
│   ├── view/AppDialogs.java                ← Sign in / Sign up / New game dialogs
│   ├── view/GamesView.java                 ← Game list + Join/Leave/Start/Play/Delete
│   ├── view/GameSelection.java
│   └── model/                              ← User, Game, Player, GameState, OnlineState
├── controller/AppController.java           ← signIn / signUp / signOut / selectGame
├── view/RoboRallyMenuBar.java              ← File menu wiring
└── ...
```

