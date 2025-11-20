package edu.io;

import edu.io.token.PlayerToken;

public class Game {

    private Board board;
    private Player player;

    public void join (Player player){
        this.player = player;
    }

    public void start (){
        Board board = new Board();
        PlayerToken playerToken = new PlayerToken(this.player,board);

        board.display();
    }




}
