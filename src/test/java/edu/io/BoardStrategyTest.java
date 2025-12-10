package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.GoldToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardStrategyTest {

    @Test
    void can_set_random_strategy() {
        Board board = new Board();
        board.setPlacementStrategy(new RandomPlacementStrategy());
        Board.Coords c = board.getAvailableSquare();
        Assertions.assertNotNull(c);
    }

    @Test
    void random_strategy_returns_empty_square() {
        Board board = new Board();
        board.setPlacementStrategy(new RandomPlacementStrategy());
        Board.Coords c = board.getAvailableSquare();
        Assertions.assertTrue(
                board.peekToken(c.col(), c.row()) instanceof EmptyToken
        );
    }

    @Test
    void throws_when_board_is_full() {
        Board board = new Board();
        board.setPlacementStrategy(new RandomPlacementStrategy());

        int n = board.size() * board.size();
        for (int i = 0; i < n; i++) {
            Board.Coords c = board.getAvailableSquare();
            board.placeToken(c.col(), c.row(), new GoldToken());
        }

        Assertions.assertThrows(
                IllegalStateException.class,
                board::getAvailableSquare
        );
    }
}
