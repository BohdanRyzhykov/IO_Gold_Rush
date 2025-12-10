package edu.io;

import edu.io.token.EmptyToken;

public class SequentialPlacementStrategy implements PlacementStrategy {
    @Override
    public Board.Coords findAvailableSquare(Board board) {
        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.size(); c++) {
                if (board.peekToken(c, r) instanceof EmptyToken) {
                    return new Board.Coords(c, r);
                }
            }
        }
        throw new IllegalStateException("Board is full");
    }
}
