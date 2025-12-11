package edu.io.token;

import edu.io.player.Player;
import edu.io.Board;


public class PlayerToken extends Token {

    public enum Move {
        LEFT, RIGHT, UP, DOWN, NONE
    }

    private final Board board;
    private int col;
    private int row;

    public PlayerToken(Player player, Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = board.size() / 2;
        this.row = board.size() / 2;
        board.placeToken(col, row, this);

    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }

    public void move(Move m) {
        if (m == Move.NONE) return;

        int newCol = col;
        int newRow = row;

        switch (m) {
            case LEFT  -> newCol--;
            case RIGHT -> newCol++;
            case UP    -> newRow--;
            case DOWN  -> newRow++;
        }

        if (newCol < 0 || newCol >= board.size()
                || newRow < 0 || newRow >= board.size()) {
            throw new IllegalArgumentException("move out of board bounds");
        }

        board.placeToken(col, row, new EmptyToken());
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this);
    }
}
