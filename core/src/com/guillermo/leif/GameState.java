package com.guillermo.leif;

public class GameState {
    public Player player1;
    public Player player2;
    public PongBall pongBall;

    int paddleRotateSpeed;

    public GameState() {
        player1 = new Player("player1", 105, GlobalVars.viewHeight / 2);
        player2 = new Player("player2", GlobalVars.viewWidth - 105,
                GlobalVars.viewHeight / 2);

        pongBall = new PongBall();
    }
}
