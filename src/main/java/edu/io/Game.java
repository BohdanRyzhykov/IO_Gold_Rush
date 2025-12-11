package edu.io;

import edu.io.player.Player;
import edu.io.token.PlayerToken;

public class Game {

    private Board board;
    private Player player;

    public void join(Player player) {
        this.player = player;
        this.board = new Board();
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
    }

    public void start() {

    }
}
