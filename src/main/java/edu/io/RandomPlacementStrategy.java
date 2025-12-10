package edu.io;

import edu.io.token.EmptyToken;
import java.util.*;

public class RandomPlacementStrategy implements PlacementStrategy {

    private final Random random = new Random();

    @Override
    public Board.Coords findAvailableSquare(Board board) {
        List<Board.Coords> free = new ArrayList<>();

        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.size(); c++) {
                if (board.peekToken(c, r) instanceof EmptyToken) {
                    free.add(new Board.Coords(c, r));
                }
            }
        }

        if (free.isEmpty()) {
            throw new IllegalStateException("Board is full");
        }

        return free.get(random.nextInt(free.size()));
    }
}