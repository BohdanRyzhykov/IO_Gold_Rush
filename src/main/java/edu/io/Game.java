package edu.io;

import edu.io.token.PlayerToken;

public class Game {

    private Board board;
    private Player player;

    public void join(Player player){
        this.player = player;
        this.board = new Board();
        PlayerToken playerToken = new PlayerToken(this.player, this.board);
        this.player.assignToken(playerToken);
    }

    public void start(){
        this.board = new Board();
        PlayerToken playerToken = new PlayerToken(this.player, this.board);
        this.player.assignToken(playerToken);
        board.display();
    }
}

